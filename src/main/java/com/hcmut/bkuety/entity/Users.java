package com.hcmut.bkuety.entity;


import com.hcmut.bkuety.dto.users.request.CreateUserRequestDTO;
import com.hcmut.bkuety.dto.users.request.UpdateUserRequestDTO;
import com.hcmut.bkuety.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Users {
    public Users(CreateUserRequestDTO dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.firstname = dto.getFirstName();
        this.lastname = dto.getLastName();
        this.mainAddress = dto.getAddress();
        this.phone = dto.getPhone();
        this.birthday = dto.getBirthday();
        List<String> addresses = new ArrayList<>();
        addresses.add(dto.getAddress());
        this.addresses = addresses;
    }
    public void updateFromDto(UpdateUserRequestDTO dto) {
        this.email = dto.getEmail();
        this.firstname = dto.getFirstName();
        this.lastname = dto.getLastName();
        this.phone = dto.getPhone();
        this.birthday = dto.getBirthday();
        this.mainAddress = dto.getMainAddress();
        this.addresses = dto.getAddresses();
        this.updatedAt = LocalDateTime.now();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private LocalDate birthday;
    private LocalDate joinDate;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String mainAddress;
    @ElementCollection
    @CollectionTable(
            name = "user_addresses", // The name of the new table
            joinColumns = @JoinColumn(name = "user_id") // The foreign key column
    )
    @Column(name = "address_detail") // The name of the string column
    private List<String>  addresses;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

