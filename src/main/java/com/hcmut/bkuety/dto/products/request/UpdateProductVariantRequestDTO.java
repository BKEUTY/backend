package com.hcmut.bkuety.dto.products.request;

import com.hcmut.bkuety.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductVariantRequestDTO {
    private Integer id;
    private String productVariantName;
    private BigDecimal price = null;
    private Integer stockQuantity = null;
    private String description = null;
    private String productImageUrl = null;
    private ProductStatus status = null;
}
