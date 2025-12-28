package com.hcmut.bkuety.service.orders;

import com.hcmut.bkuety.dto.cartItems.AddToCartResponseDTO;
import com.hcmut.bkuety.dto.orders.OrderItemDTO;
import com.hcmut.bkuety.dto.orders.PlaceOrderRequest;
import com.hcmut.bkuety.dto.orders.OrderResponseDTO;
import com.hcmut.bkuety.entity.CartItems;
import com.hcmut.bkuety.entity.OrderItems;
import com.hcmut.bkuety.entity.Orders;
import com.hcmut.bkuety.entity.Products;
import com.hcmut.bkuety.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    private UsersRepository usersRepository;
    @Autowired
    private CartItemsRepository cartItemsRepository;
    public ResponseEntity<OrderResponseDTO> placeOrder (PlaceOrderRequest request){
        List<OrderItemDTO> orderItemList = request.getOrderItems();
        Orders order = new Orders();
        order.setOrderDate(LocalDate.now());
        order.setAddress(request.getAddress());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setUser(usersRepository.findById(request.getUserId()).get());
        order.setTotal(0d);
        Orders orderSave = ordersRepository.save(order);
        Double totalAmount = 0d;
        List<AddToCartResponseDTO> items = new ArrayList<>();
        for(OrderItemDTO orderItemDto : orderItemList){
            OrderItems  orderItem = new OrderItems();
            CartItems cartItems = cartItemsRepository.findById(orderItemDto.getCartItemId()).get();
            Products product = cartItems.getProduct();
            orderItem.setOrder(orderSave);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItems.getQuantity());
            totalAmount = totalAmount +  product.getPrice()*orderItem.getQuantity();
            AddToCartResponseDTO addToCartResponseDTO = new AddToCartResponseDTO();
            addToCartResponseDTO.setProductId(product.getId());
            addToCartResponseDTO.setQuantity(cartItems.getQuantity());
            addToCartResponseDTO.setPrice(product.getPrice());
            addToCartResponseDTO.setProductName(product.getName());
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
            Products products = orderItems.getProduct();
            AddToCartResponseDTO addToCartResponseDTO = new AddToCartResponseDTO();
            addToCartResponseDTO.setProductId(products.getId());
            addToCartResponseDTO.setQuantity(orderItems.getQuantity());
            addToCartResponseDTO.setPrice(products.getPrice());
            addToCartResponseDTO.setProductName(products.getName());
            itemList.add(addToCartResponseDTO);
        }
        return itemList;
    }
}
