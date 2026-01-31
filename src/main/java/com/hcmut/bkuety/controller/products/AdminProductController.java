package com.hcmut.bkuety.controller.products;

import com.hcmut.bkuety.dto.products.ProductResponseDTO;
import com.hcmut.bkuety.dto.products.ProductVariantDTO;
import com.hcmut.bkuety.dto.products.request.CreateProductOptionDTO;
import com.hcmut.bkuety.dto.products.request.CreateProductRequestDTO;
import com.hcmut.bkuety.dto.products.request.UpdateProductRequestDTO;
import com.hcmut.bkuety.dto.products.request.UpdateProductVariantRequestDTO;
import com.hcmut.bkuety.dto.products.response.CreateProductResponseDTO;
import com.hcmut.bkuety.service.products.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("admin/api/product")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }
    @GetMapping("/{productId}/variants")
    public ResponseEntity<List<ProductVariantDTO>> getAllProductVariants(
            @PathVariable Integer productId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductVariants(productId));
    }
    @PostMapping()
    public ResponseEntity createProduct(@Valid @RequestBody CreateProductRequestDTO createProductRequestDTO) {
        CreateProductResponseDTO savedProduct = productService.createProduct(createProductRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
    @PutMapping()
    public ResponseEntity updateProduct(@Valid @RequestBody UpdateProductRequestDTO updateProductRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(updateProductRequestDTO));
    }
    @PutMapping("/variants")
    public ResponseEntity updateProductVariant(@Valid @RequestBody UpdateProductVariantRequestDTO updateProductVariantRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductVariant(updateProductVariantRequestDTO));
    }
    @PostMapping("/options")
    public ResponseEntity createOption(@Valid @RequestBody CreateProductOptionDTO createProductOptionDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createOptionValue(createProductOptionDTO));
    }
}
