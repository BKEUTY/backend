package com.hcmut.bkuety.service.products;

import com.hcmut.bkuety.dto.products.ProductResponseDTO;
import com.hcmut.bkuety.dto.products.ProductVariantDTO;
import com.hcmut.bkuety.dto.products.request.*;
import com.hcmut.bkuety.dto.products.response.CreateProductResponseDTO;
import com.hcmut.bkuety.dto.products.response.UpdateProductResponseDTO;
import com.hcmut.bkuety.dto.products.response.UpdateProductVariantResponseDTO;
import com.hcmut.bkuety.entity.*;
import com.hcmut.bkuety.exception.ProductNotFoundException;
import com.hcmut.bkuety.exception.ProductVariantNotFoundException;
import com.hcmut.bkuety.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductCategoriesRepository productCategoriesRepository;
    @Autowired
    private ProductOptionsRepository productOptionsRepository;
    @Autowired
    private ProductOptionValueRepository productOptionValueRepository;
    @Autowired
    private ProductVariantsRepository productVariantsRepository;

    public List<ProductResponseDTO> getAllProducts() {
        return productsRepository.findAll().stream().map(this::toProductResponseDto).toList();
    }

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productsRepository.findAll(pageable).map(this::toProductResponseDto);
    }

    public List<ProductVariantDTO> getAllProductVariants(Integer productId) {
        return productVariantsRepository.findAllByProductId(productId).stream().map(this::toProductVariantDTO).toList();
    }

    public ProductResponseDTO getProductById(Integer productId) {
        return productsRepository.findById(productId)
                .map(this::toProductResponseDto)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    private ProductResponseDTO toProductResponseDto(Products product) {
        List<ProductOptions> options = productOptionsRepository.findAllByProductId(product.getId());
        List<ProductOptionValueDTO> optionDtos = options.stream().map(opt -> {
            List<String> values = productOptionValueRepository.findAllByProductId(opt.getId())
                    .stream().map(ProductOptionValue::getOptionValueName).toList();
            return new ProductOptionValueDTO(opt.getOptionName(), values);
        }).toList();

        return ProductResponseDTO.builder()
                .productId(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .description(product.getDescription())
                .categories(product.getCategories().stream().map(Categories::getCategoryName).toList())
                .options(optionDtos)
                .build();
    }

    public CreateProductResponseDTO createProduct(CreateProductRequestDTO requestDTO) {
        Products product = Products.builder()
                .name(requestDTO.getName())
                .description(requestDTO.getDescription())
                .image(requestDTO.getImage()).build();
        Set<Categories> setCategories = requestDTO.getProductCategories().stream()
                .map(categoryId -> productCategoriesRepository.findById(categoryId).get()).filter(Objects::nonNull)
                .collect(Collectors.toSet());
        product.setCategories(setCategories);
        return toCreateProductResponseDTO(productsRepository.save(product));

    }

    public CreateProductResponseDTO toCreateProductResponseDTO(Products product) {
        CreateProductResponseDTO createProductResponseDTO = new CreateProductResponseDTO();
        createProductResponseDTO.setId(product.getId());
        createProductResponseDTO.setName(product.getName());
        createProductResponseDTO.setDescription(product.getDescription());
        createProductResponseDTO.setImage(product.getImage());
        createProductResponseDTO.setCategories(
                product.getCategories().stream().map(Categories::getCategoryName).collect(Collectors.toList()));
        return createProductResponseDTO;

    }

    public List<ProductVariantDTO> createOptionValue(CreateProductOptionDTO requestDTO) {
        List<List<ProductOptionValue>> optionValues = new ArrayList<>();
        List<ProductOptionValueDTO> productOptions = requestDTO.getProductOptionValues();
        Products product = productsRepository.findById(requestDTO.getProductId()).get();
        for (ProductOptionValueDTO option : productOptions) {
            ProductOptions newOption = new ProductOptions();
            newOption.setProduct(product);
            newOption.setOptionName(option.getOptionName());
            newOption = productOptionsRepository.save(newOption);
            List<ProductOptionValue> productOptionValueEntities = new ArrayList<>();
            for (String optionValue : option.getOptionValues()) {

                ProductOptionValue newOptionValue = new ProductOptionValue();
                newOptionValue.setOptionValueName(optionValue);
                newOptionValue.setProduct(newOption);
                newOptionValue = productOptionValueRepository.save(newOptionValue);
                productOptionValueEntities.add(newOptionValue);
            }
            optionValues.add(productOptionValueEntities);
        }
        List<List<ProductOptionValue>> variantCombinations = generateCombinations(optionValues);
        return createVariantProduct(variantCombinations, product).stream().map(this::toProductVariantDTO)
                .collect(Collectors.toList());
    }

    public List<ProductVariant> createVariantProduct(List<List<ProductOptionValue>> combinations, Products product) {
        List<ProductVariant> result = new ArrayList<>();
        for (List<ProductOptionValue> productOptionValues : combinations) {
            ProductVariant variant = new ProductVariant();
            variant.setProduct(product);
            variant.setOptionValues(new HashSet<>(productOptionValues));
            variant.setDescription(product.getDescription());
            variant.setPrice(BigDecimal.ZERO);
            variant.setStockQuantity(0);
            variant.setProductImageUrl(product.getImage());
            variant.setProductVariantName(product.getName());
            result.add(productVariantsRepository.save(variant));
        }
        return result;
    }

    private ProductVariantDTO toProductVariantDTO(ProductVariant productVariant) {
        return ProductVariantDTO.builder()
                .id(productVariant.getId())
                .productImageUrl(productVariant.getProductImageUrl())
                .productName(productVariant.getProduct().getName())
                .price(productVariant.getPrice())
                .optionValues(
                        productVariant.getOptionValues().stream().map(ProductOptionValue::getOptionValueName).toList())
                .status(productVariant.getStatus())
                .stockQuantity(productVariant.getStockQuantity())
                .description(productVariant.getDescription())
                .productVariantName(productVariant.getProductVariantName())
                .build();
    }

    public List<List<ProductOptionValue>> generateCombinations(List<List<ProductOptionValue>> options) {
        List<List<ProductOptionValue>> result = new ArrayList<>();
        combine(options, 0, new ArrayList<>(), result);
        return result;
    }

    private void combine(List<List<ProductOptionValue>> options, int depth,
            List<ProductOptionValue> current,
            List<List<ProductOptionValue>> result) {
        if (depth == options.size()) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (ProductOptionValue value : options.get(depth)) {
            current.add(value);
            combine(options, depth + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public UpdateProductResponseDTO updateProduct(UpdateProductRequestDTO requestDTO) {

        return productsRepository.findById(requestDTO.getId())
                .map(products -> applyUpdateProduct(products, requestDTO))
                .map(productsRepository::save)
                .map(this::toUpdateProduct)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
    }

    private Products applyUpdateProduct(Products updateProduct, UpdateProductRequestDTO dto) {
        Optional.ofNullable(dto.getDescription()).ifPresent(updateProduct::setDescription);
        Optional.ofNullable(dto.getName()).ifPresent(updateProduct::setName);
        Optional.ofNullable(dto.getImage()).ifPresent(updateProduct::setImage);
        if (dto.getProductCategories() != null) {
            Set<Categories> categories = dto.getProductCategories().stream()
                    .map(productCategoriesRepository::findById)
                    .flatMap(Optional::stream)
                    .collect(Collectors.toSet());
            updateProduct.setCategories(categories);
        }

        return updateProduct;
    }

    private UpdateProductResponseDTO toUpdateProduct(Products product) {
        List<String> categories = product.getCategories().stream().map(Categories::getCategoryName).toList();
        return UpdateProductResponseDTO.builder()
                .name(product.getName())
                .productCategories(categories)
                .image(product.getImage())
                .description(product.getDescription())
                .build();
    }

    public UpdateProductVariantResponseDTO updateProductVariant(UpdateProductVariantRequestDTO requestDTO) {
        return productVariantsRepository.findById(requestDTO.getId())
                .map(productVariant -> applyUpdateProductVariant(productVariant, requestDTO))
                .map(productVariantsRepository::save)
                .map(this::toUpdateProductVariant)
                .orElseThrow(() -> new ProductVariantNotFoundException("Product variant not Found"));

    }

    private ProductVariant applyUpdateProductVariant(ProductVariant productVariant,
            UpdateProductVariantRequestDTO dto) {
        if (dto.getProductVariantName() != null)
            productVariant.setProductVariantName(dto.getProductVariantName());
        if (dto.getProductImageUrl() != null)
            productVariant.setProductImageUrl(dto.getProductImageUrl());
        if (dto.getStockQuantity() != null)
            productVariant.setStockQuantity(dto.getStockQuantity());
        if (dto.getDescription() != null)
            productVariant.setDescription(dto.getDescription());
        if (dto.getPrice() != null)
            productVariant.setPrice(dto.getPrice());
        if (dto.getStatus() != null)
            productVariant.setStatus(dto.getStatus());
        return productVariant;
    }

    private UpdateProductVariantResponseDTO toUpdateProductVariant(ProductVariant productVariant) {
        return UpdateProductVariantResponseDTO.builder()
                .productVariantName(productVariant.getProductVariantName())
                .description(productVariant.getDescription())
                .productName(productVariant.getProduct().getName())
                .productImageUrl(productVariant.getProductImageUrl())
                .price(productVariant.getPrice())
                .stockQuantity(productVariant.getStockQuantity())
                .optionValues(
                        productVariant.getOptionValues().stream().map(ProductOptionValue::getOptionValueName).toList())
                .build();
    }

}
