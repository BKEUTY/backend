package com.hcmut.bkuety.dto.products.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOptionValueDTO {
    private String optionName;
    private List<String> optionValues;
}
