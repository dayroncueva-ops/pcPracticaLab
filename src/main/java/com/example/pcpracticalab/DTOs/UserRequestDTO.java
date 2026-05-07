package com.example.pcpracticalab.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserRequestDTO {


    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = ".*[A-Z].*")
    private String firstName;

    @NotBlank
    @Pattern(regexp = ".*[A-Z].*")
    private String lastName;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
    )
    private String password;
}