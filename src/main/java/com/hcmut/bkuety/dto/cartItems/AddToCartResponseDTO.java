package com.hcmut.bkuety.dto.cartItems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddToCartResponseDTO {
    private Integer productVariantId;
    private String productVariantName;
    private String productVariantImage;
    private Integer quantity;
    private BigDecimal price;
}
