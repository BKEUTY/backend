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
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
}
