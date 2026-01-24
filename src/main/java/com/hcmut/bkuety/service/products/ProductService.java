package com.hcmut.bkuety.service.products;

import com.hcmut.bkuety.dto.products.ProductResponseDTO;
import com.hcmut.bkuety.entity.Products;
import com.hcmut.bkuety.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<ProductResponseDTO> getAllProducts() {
        return productsRepository.findAll().stream().map(this::toProductResponseDto).toList();
    }

    public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
        return productsRepository.findAll(pageable).map(this::toProductResponseDto);
    }

    private ProductResponseDTO toProductResponseDto(Products product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getImage());
        return productResponseDTO;
    }
}
