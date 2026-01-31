package com.hcmut.bkuety.dto.users.response;

import com.hcmut.bkuety.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDetailForAdminResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate birthday;
    private LocalDate joinDate;
    private UserStatus status;
    private String mainAddress;
    private List<String> addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
