package com.hcmut.bkuety.controller.cartItems;

import com.hcmut.bkuety.dto.cartItems.AddToCartRequest;
import com.hcmut.bkuety.dto.cartItems.AddToCartResponseDTO;
import com.hcmut.bkuety.dto.cartItems.CartItemResponseDTO;
import com.hcmut.bkuety.repository.CartItemsRepository;
import com.hcmut.bkuety.service.cartItems.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartItemsController {
    @Autowired
    private CartItemsService cartItemsService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponseDTO>> getCartItems(@PathVariable Integer userId){
        return ResponseEntity.ok(cartItemsService.getListCartItem(userId));
    }
    @PostMapping()
    public ResponseEntity<AddToCartResponseDTO> addToCart(@RequestBody AddToCartRequest request){
        return cartItemsService.addToCart(request);
    }
}
