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
public class CartItemResponseDTO {
    private Integer cartId;
    private Integer productId;
    private Integer productVariantId;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private Integer quantity;
}
