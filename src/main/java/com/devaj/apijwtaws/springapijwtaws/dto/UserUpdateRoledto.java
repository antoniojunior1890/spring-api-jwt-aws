package com.devaj.apijwtaws.springapijwtaws.dto;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UserUpdateRoledto {

    @NotNull(message = "Role requerido")
    private Role role;
}
