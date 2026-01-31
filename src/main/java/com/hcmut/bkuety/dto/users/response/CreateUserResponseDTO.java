package com.hcmut.bkuety.dto.users.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserResponseDTO {
    private Integer userId;
    private String username;
}
