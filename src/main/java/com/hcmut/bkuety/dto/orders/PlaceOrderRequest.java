package com.hcmut.bkuety.dto.orders;

import com.hcmut.bkuety.dto.cartItems.AddToCartRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequest {
    private Integer userId;
    private String paymentMethod;
    private String address;
    private List<OrderItemDTO> orderItems;
}
