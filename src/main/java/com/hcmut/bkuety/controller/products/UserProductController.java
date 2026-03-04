package com.hcmut.bkuety.controller.products;

import com.hcmut.bkuety.dto.products.ProductResponseDTO;
import com.hcmut.bkuety.dto.products.ProductVariantDTO;
import com.hcmut.bkuety.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class UserProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductDetail(
            @PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/{productId}/variants")
    public ResponseEntity<List<ProductVariantDTO>> getProductVariants(
            @PathVariable Integer productId) {
        return ResponseEntity.ok(productService.getAllProductVariants(productId));
    }
}
