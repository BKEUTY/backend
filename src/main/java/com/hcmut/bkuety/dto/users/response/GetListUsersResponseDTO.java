package com.hcmut.bkuety.dto.users.response;

import com.hcmut.bkuety.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetListUsersResponseDTO {
    Integer userId;
    Integer username;
    UserStatus status;
}
