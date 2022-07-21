package me.reimarrosas.strelloapi.domain.user.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSignupDto extends UserLoginDto {
    @NotBlank
    private String name;
}
