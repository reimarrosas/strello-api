package me.reimarrosas.strelloapi.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginDto {
    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z]).{8,}$")
    @NotBlank
    private String password;
}
