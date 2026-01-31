package com.hcmut.bkuety.controller.users;

import com.hcmut.bkuety.dto.users.request.UpdateUserRequestDTO;
import com.hcmut.bkuety.dto.users.response.DeleteUserResponseDTO;
import com.hcmut.bkuety.dto.users.response.GetUserDetailForUserResponseDTO;
import com.hcmut.bkuety.dto.users.response.UpdateUserResponseDTO;
import com.hcmut.bkuety.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/api/detail")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private Integer getUserId(){
        return 1;
    }
    @GetMapping()
    public ResponseEntity<GetUserDetailForUserResponseDTO> getUserDetailForUserResponseDTOResponseEntity() {
        return ResponseEntity.ok(userService.getUserDetail(getUserId()));
    }
    @PutMapping()
    public ResponseEntity<UpdateUserResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(getUserId(),updateUserRequestDTO));
    }
    @DeleteMapping()
    public ResponseEntity deleteUser() {
        return userService.deleteUser(getUserId());
    }
}
