package com.hcmut.bkuety.controller.orders;

import com.hcmut.bkuety.dto.orders.OrderResponseDTO;
import com.hcmut.bkuety.dto.orders.PlaceOrderRequest;
import com.hcmut.bkuety.entity.OrderItems;
import com.hcmut.bkuety.repository.OrderItemsRepository;
import com.hcmut.bkuety.service.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponseDTO>> findOrderByUserId(@PathVariable Integer userId) {
        return orderService.getListOrders(userId);
    }
    @PostMapping
    public ResponseEntity<OrderResponseDTO> placeOrder (@RequestBody PlaceOrderRequest placeOrderRequest) {
        return orderService.placeOrder(placeOrderRequest);
    }

}
