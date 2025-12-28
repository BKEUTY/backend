package com.hcmut.bkuety.dto.cartItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDTO {
    private Integer cartId;
    private Integer productId;
    private String name;
    private String description;
    private Double price;
    private String image;
    private Integer quantity;
}
