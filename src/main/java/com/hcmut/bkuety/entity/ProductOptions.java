package com.hcmut.bkuety.entity;

import com.hcmut.bkuety.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductOptions {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ProductStatus status = ProductStatus.ACTIVE;
    private String optionName;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
}
