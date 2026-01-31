package com.hcmut.bkuety.service.cartItems;

import com.hcmut.bkuety.dto.cartItems.AddToCartRequest;
import com.hcmut.bkuety.dto.cartItems.AddToCartResponseDTO;
import com.hcmut.bkuety.dto.cartItems.CartItemResponseDTO;
import com.hcmut.bkuety.dto.products.ProductResponseDTO;
import com.hcmut.bkuety.entity.CartItems;
import com.hcmut.bkuety.entity.ProductVariant;
import com.hcmut.bkuety.entity.Products;
import com.hcmut.bkuety.entity.Users;
import com.hcmut.bkuety.exception.ProductVariantNotFoundException;
import com.hcmut.bkuety.exception.UserNotFoundException;
import com.hcmut.bkuety.repository.CartItemsRepository;
import com.hcmut.bkuety.repository.ProductVariantsRepository;
import com.hcmut.bkuety.repository.ProductsRepository;
import com.hcmut.bkuety.repository.UsersRepository;
import com.hcmut.bkuety.service.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemsService {
    @Autowired
    private CartItemsRepository cartItemsRepository;
    @Autowired
    private ProductVariantsRepository productVariantsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<CartItemResponseDTO> getListCartItem (Integer userId) {
        return cartItemsRepository.findByUserId(userId).stream().map(this::toCartItemResponseDTO).toList();
    }
    public CartItemResponseDTO toCartItemResponseDTO(CartItems cartItems) {

        return CartItemResponseDTO.builder()
                .productVariantId(cartItems.getProductVariant().getId())
                .cartId(cartItems.getId())
                .price(cartItems.getProductVariant().getPrice())
                .image(cartItems.getProductVariant().getProductImageUrl())
                .description(cartItems.getProductVariant().getDescription())
                .name(cartItems.getProductVariant().getProductVariantName())
                .productId(cartItems.getProductVariant().getProduct().getId())
                .build();
    }
    public AddToCartResponseDTO toAddToCartResponseDTO(CartItems cartItems) {
        return  AddToCartResponseDTO.builder()
                .quantity(cartItems.getQuantity())
                .price(cartItems.getProductVariant().getPrice())
                .productVariantId(cartItems.getProductVariant().getId())
                .productVariantImage(cartItems.getProductVariant().getProductImageUrl())
                .productVariantName(cartItems.getProductVariant().getProductVariantName())
                .build();
    }
    public ResponseEntity<AddToCartResponseDTO> addToCart(AddToCartRequest addToCartRequest) {
        CartItems itemInCartItem = cartItemsRepository.findByUserIdAndProductVariantId(addToCartRequest.getUserId(),addToCartRequest.getProductVariantId());

        if(itemInCartItem!= null){
            itemInCartItem.setQuantity(itemInCartItem.getQuantity()+addToCartRequest.getQuantity());
            cartItemsRepository.save(itemInCartItem);
        }
        Users user = usersRepository.findById(addToCartRequest.getUserId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        ProductVariant productVariant = productVariantsRepository.findById(addToCartRequest.getProductVariantId()).orElseThrow(() -> new ProductVariantNotFoundException("Can not find product SKU"));
        CartItems cartItems  = CartItems.builder()
                                        .productVariant(productVariant)
                                        .quantity(addToCartRequest.getQuantity())
                                        .user(user).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(toAddToCartResponseDTO(cartItemsRepository.save(cartItems)));

    }
}
