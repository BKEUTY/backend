package com.hcmut.bkuety.service.users;

import com.hcmut.bkuety.dto.users.request.UpdateUserRequestDTO;
import com.hcmut.bkuety.dto.users.response.DeleteUserResponseDTO;
import com.hcmut.bkuety.dto.users.response.GetUserDetailForUserResponseDTO;
import com.hcmut.bkuety.dto.users.response.UpdateUserResponseDTO;
import com.hcmut.bkuety.entity.Users;
import com.hcmut.bkuety.enums.UserStatus;
import com.hcmut.bkuety.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public GetUserDetailForUserResponseDTO getUserDetail(Integer userId) {
        return usersRepository.findById(userId)
                .map(this::toGetUserDetailForUserResponseDTO)
                .orElseThrow(() -> {
                    logger.warn("User not found with ID: {}", userId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                });
    }
    public UpdateUserResponseDTO updateUser(Integer userId, UpdateUserRequestDTO updateUserRequestDTO) {
        return usersRepository.findById(userId)
                .map(user -> {
                    user.updateFromDto(updateUserRequestDTO); // Logic moved to Entity
                    return usersRepository.save(user);
                })
                .map(this::toUpdateUserResponseDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
    public ResponseEntity deleteUser(Integer userId) {
         Optional<Users> user = usersRepository.findById(userId);
         if(user.isPresent()) {
             user.get().setStatus(UserStatus.INACTIVE);
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
         }
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    private UpdateUserResponseDTO toUpdateUserResponseDTO(Users user) {
        return new UpdateUserResponseDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthday(),
                user.getMainAddress(),
                user.getAddresses()
        );
    }
    private GetUserDetailForUserResponseDTO toGetUserDetailForUserResponseDTO(Users user) {
        return new GetUserDetailForUserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getBirthday(),
                user.getJoinDate(),
                user.getMainAddress(),
                user.getAddresses()
        );
    }
}
