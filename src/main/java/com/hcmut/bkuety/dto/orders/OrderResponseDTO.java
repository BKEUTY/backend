package com.hcmut.bkuety.dto.orders;

import com.hcmut.bkuety.dto.cartItems.AddToCartResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private Double total;
    private String paymentMethod;
    private LocalDate orderDate;
    private String address;
    private List<AddToCartResponseDTO> items;
}
