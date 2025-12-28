package com.hcmut.bkuety.dto.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {
    private Integer productId;
    private String name;
    private String description;
    private Double price;
    private String image;

}
