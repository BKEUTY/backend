package com.hcmut.bkuety.service.cartItems;

import com.hcmut.bkuety.dto.cartItems.AddToCartRequest;
import com.hcmut.bkuety.dto.cartItems.AddToCartResponseDTO;
import com.hcmut.bkuety.dto.cartItems.CartItemResponseDTO;
import com.hcmut.bkuety.dto.products.ProductResponseDTO;
import com.hcmut.bkuety.entity.CartItems;
import com.hcmut.bkuety.entity.Products;
import com.hcmut.bkuety.entity.Users;
import com.hcmut.bkuety.repository.CartItemsRepository;
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
    private ProductsRepository productsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<CartItemResponseDTO> getListCartItem (Integer userId) {
        return cartItemsRepository.findByUserId(userId).stream().map(this::toCartItemResponseDTO).toList();
    }
    public CartItemResponseDTO toCartItemResponseDTO(CartItems cartItems) {
        Products prod = cartItems.getProduct();
        return new CartItemResponseDTO(cartItems.getId(),prod.getId(),prod.getName(),prod.getDescription(),null,prod.getImage(),cartItems.getQuantity());
    }
    public AddToCartResponseDTO toAddToCartResponseDTO(CartItems cartItems) {
        return new AddToCartResponseDTO(cartItems.getProduct().getId(),1,null,cartItems.getProduct().getName());
    }
    public ResponseEntity<AddToCartResponseDTO> addToCart(AddToCartRequest addToCartRequest) {
        Products prod = productsRepository.findById(addToCartRequest.getProductId()).get();
        Users user = usersRepository.findById(addToCartRequest.getUserId()).get();
        CartItems cartItems  = new CartItems();
        cartItems.setProduct(prod);
        cartItems.setUser(user);
        cartItems.setQuantity(1);
        return ResponseEntity.status(HttpStatus.CREATED).body(toAddToCartResponseDTO(cartItemsRepository.save(cartItems)));

    }
}
