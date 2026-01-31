package com.hcmut.bkuety.exception;

import com.hcmut.bkuety.exception.error.ProductNotFoundErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        ProductNotFoundErrorResponse errorResponse = new ProductNotFoundErrorResponse(e.getMessage());
        return  ResponseEntity.badRequest().body(errorResponse.getMessage());
    }
    @ExceptionHandler(ProductVariantNotFoundException.class)
    public ResponseEntity<String> handleProductVariantNotFoundException(ProductVariantNotFoundException e) {
        return  ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return  ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(CartItemNotFound.class)
    public ResponseEntity<CartItemNotFound> handleCartItemNotFound(CartItemNotFound e) {
        return  ResponseEntity.badRequest().body(new CartItemNotFound(e.getMessage(),e.getCartItemId()));
    }
}
