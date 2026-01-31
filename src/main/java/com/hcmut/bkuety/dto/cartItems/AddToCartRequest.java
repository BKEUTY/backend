package com.hcmut.bkuety.dto.cartItems;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {
    private Integer productVariantId;
    private Integer userId;
    private Integer quantity;
}
