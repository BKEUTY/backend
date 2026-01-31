package com.hcmut.bkuety.dto.products;

import com.hcmut.bkuety.entity.ProductOptionValue;
import com.hcmut.bkuety.entity.Products;
import com.hcmut.bkuety.enums.ProductStatus;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariantDTO {
    private Integer id;
    private BigDecimal price;
    private String productVariantName;
    private Integer stockQuantity;
    private String description;
    private String productImageUrl;
    private List<String> optionValues;
    private ProductStatus status;
    private String productName;
}
