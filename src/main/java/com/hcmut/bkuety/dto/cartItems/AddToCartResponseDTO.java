package com.hcmut.bkuety.dto.cartItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartResponseDTO {
    private Integer productId;
    private Integer quantity;
    private Double price;
    private String productName;
}
