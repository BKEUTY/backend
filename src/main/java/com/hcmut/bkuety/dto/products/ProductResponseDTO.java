package com.hcmut.bkuety.dto.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private Integer productId;
    private String name;
    private String description;
    private String image;
    private List<String> categories;

}
