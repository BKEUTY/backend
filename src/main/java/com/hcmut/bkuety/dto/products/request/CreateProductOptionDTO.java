package com.hcmut.bkuety.dto.products.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductOptionDTO {
    private Integer productId;
    @NotEmpty
    List<ProductOptionValueDTO> productOptionValues;
}
