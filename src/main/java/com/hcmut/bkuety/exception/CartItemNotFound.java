package com.hcmut.bkuety.exception;

import lombok.Data;

@Data
public class CartItemNotFound extends RuntimeException {
    private Integer cartItemId;

    public CartItemNotFound(String message, Integer CartItemId) {

        super(message);
        this.cartItemId = CartItemId;
    }
}
