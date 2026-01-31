package com.hcmut.bkuety.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
