package com.hcmut.bkuety.service.auth;

import com.hcmut.bkuety.dto.users.request.CreateUserRequestDTO;
import com.hcmut.bkuety.dto.users.response.CreateUserResponseDTO;
import com.hcmut.bkuety.entity.Users;
import com.hcmut.bkuety.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class AuthService {
    private final UsersRepository usersRepository;
    Logger logger = LoggerFactory.getLogger(AuthService.class);
    public AuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public ResponseEntity<CreateUserResponseDTO> registerUser(CreateUserRequestDTO createUserRequestDTO){
        try {
            if (usersRepository.existsByUsername(createUserRequestDTO.getUsername())) {
                logger.warn("Registration failed: Username '{}' is already taken", createUserRequestDTO.getUsername());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username not valid");
            }
            Users user = new Users(createUserRequestDTO);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setJoinDate(LocalDate.now());

            Users savedUser = usersRepository.save(user);
            logger.info("Successfully registered user: {}", savedUser.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(toCreateUserResponseDTO(savedUser));

        } catch (DataIntegrityViolationException e) {
            logger.warn("Registration conflict: Username or Email already exists - {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists", e);
        } catch (Exception e) {
            logger.error("Unexpected error during registration for user {}: ", createUserRequestDTO.getUsername(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Registration failed", e);
        }
    }
    public CreateUserResponseDTO toCreateUserResponseDTO(Users user){
        return new CreateUserResponseDTO(user.getId(),user.getUsername());
    }
}
