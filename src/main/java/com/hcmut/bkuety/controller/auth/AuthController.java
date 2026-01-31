package com.hcmut.bkuety.controller.auth;

import com.hcmut.bkuety.dto.users.request.CreateUserRequestDTO;
import com.hcmut.bkuety.dto.users.response.CreateUserResponseDTO;
import com.hcmut.bkuety.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class AuthController {
    private final AuthService authService;
    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseDTO> registerUser(@RequestBody CreateUserRequestDTO createUserRequestDTO){
        return authService.registerUser(createUserRequestDTO);
    }

}
