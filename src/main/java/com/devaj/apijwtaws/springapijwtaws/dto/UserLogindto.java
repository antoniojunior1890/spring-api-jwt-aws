package com.devaj.apijwtaws.springapijwtaws.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserLogindto {

    @Email(message = "Email invalido")
    private String email;

    @NotBlank(message = "Senha requerida")
    private String password;
}
