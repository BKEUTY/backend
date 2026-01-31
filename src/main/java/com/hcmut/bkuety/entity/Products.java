package com.hcmut.bkuety.entity;

import com.hcmut.bkuety.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name="product_categories",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private Set<Categories> categories =  new HashSet<>();
    private String image;
    private ProductStatus status = ProductStatus.ACTIVE;
}
