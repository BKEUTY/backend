package com.hcmut.bkuety.service.orders;

import com.hcmut.bkuety.dto.cartItems.AddToCartResponseDTO;
import com.hcmut.bkuety.dto.orders.OrderItemDTO;
import com.hcmut.bkuety.dto.orders.PlaceOrderRequest;
import com.hcmut.bkuety.dto.orders.OrderResponseDTO;
import com.hcmut.bkuety.entity.*;
import com.hcmut.bkuety.exception.CartItemNotFound;
import com.hcmut.bkuety.exception.UserNotFoundException;
import com.hcmut.bkuety.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderItemsRepository orderItemsRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductVariantsRepository productVariantsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CartItemsRepository cartItemsRepository;
    public ResponseEntity<OrderResponseDTO> placeOrder (PlaceOrderRequest request){
        List<OrderItemDTO> orderItemList = request.getOrderItems();
        Users orderUser = usersRepository.findById(request.getUserId()).orElseThrow(()->new UserNotFoundException("User not found"));
        Orders order = Orders.builder()
                .orderDate(LocalDate.now())
                .address(request.getAddress())
                .paymentMethod(request.getPaymentMethod()).build();

        Orders orderSave = ordersRepository.save(order);
        BigDecimal totalAmount = BigDecimal.valueOf(0);
        List<AddToCartResponseDTO> items = new ArrayList<>();
        for(OrderItemDTO orderItemDto : orderItemList){
            OrderItems  orderItem = new OrderItems();
            CartItems cartItems = cartItemsRepository.findById(orderItemDto.getCartItemId()).orElseThrow(()->new CartItemNotFound("Cart item not found",orderItemDto.getCartItemId()));
            ProductVariant productVariant = cartItems.getProductVariant();

            orderItem.setOrder(orderSave);
            orderItem.setProductVariant(productVariant);
            orderItem.setQuantity(cartItems.getQuantity());
            totalAmount = totalAmount.add(
                    productVariant.getPrice().multiply(BigDecimal.valueOf(cartItems.getQuantity()))
            );
            Integer currentStock = productVariant.getStockQuantity();
            productVariant.setStockQuantity(currentStock - cartItems.getQuantity());
            productVariantsRepository.save(productVariant);
            AddToCartResponseDTO addToCartResponseDTO = AddToCartResponseDTO.builder()
                                                        .price(productVariant.getPrice())
                    .productVariantId(productVariant.getId())
                    .productVariantName(productVariant.getProductVariantName())
                    .quantity(cartItems.getQuantity())
                    .build();
            items.add(addToCartResponseDTO);
            cartItemsRepository.delete(cartItems);
            orderItemsRepository.save(orderItem);
        }
        orderSave.setTotal(totalAmount);
        ordersRepository.save(orderSave);
        OrderResponseDTO placeOrderResponseDTO = new OrderResponseDTO();
        placeOrderResponseDTO.setOrderDate(LocalDate.now());
        placeOrderResponseDTO.setAddress(request.getAddress());
        placeOrderResponseDTO.setPaymentMethod(request.getPaymentMethod());
        placeOrderResponseDTO.setTotal(totalAmount);
        placeOrderResponseDTO.setItems(items);
        return ResponseEntity.ok(placeOrderResponseDTO);
    }
    public ResponseEntity<List<OrderResponseDTO>> getListOrders(Integer userId){
        List<Orders> listOrders = ordersRepository.findByUserId(userId);
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();
        for(Orders orders : listOrders){
            List<OrderItems> items = orderItemsRepository.findByOrderId(orders.getId());
            orderResponseDTOList.add(toOrderResponseDTO(orders, items));
        }
        return ResponseEntity.ok(orderResponseDTOList);
    }
    public OrderResponseDTO toOrderResponseDTO (Orders order, List<OrderItems> items){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setOrderDate(LocalDate.now());
        orderResponseDTO.setAddress(order.getAddress());
        orderResponseDTO.setPaymentMethod(order.getPaymentMethod());
        orderResponseDTO.setTotal(order.getTotal());
        List<AddToCartResponseDTO> itemList = getAddToCartResponseDTOS(items);
        orderResponseDTO.setItems(itemList);
        return orderResponseDTO;
    }

    private static List<AddToCartResponseDTO> getAddToCartResponseDTOS(List<OrderItems> items) {
        List<AddToCartResponseDTO> itemList = new ArrayList<>();
        for(OrderItems orderItems : items){
            ProductVariant productVariant = orderItems.getProductVariant();
            AddToCartResponseDTO addToCartResponseDTO = new AddToCartResponseDTO();
            addToCartResponseDTO.setProductVariantId(productVariant.getId());
            addToCartResponseDTO.setQuantity(orderItems.getQuantity());
//            addToCartResponseDTO.setPrice(products.getPrice());
            addToCartResponseDTO.setProductVariantName(productVariant.getProductVariantName());
            addToCartResponseDTO.setProductVariantImage(productVariant.getProductImageUrl());
            itemList.add(addToCartResponseDTO);
        }
        return itemList;
    }
}
