package com.hcmut.bkuety.dto.users.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDetailForUserResponseDTO {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private LocalDate birthday;
    private LocalDate joinDate;
    private String mainAddress;
    private List<String> addresses;
}
