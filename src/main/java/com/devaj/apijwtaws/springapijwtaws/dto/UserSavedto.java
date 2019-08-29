package com.devaj.apijwtaws.springapijwtaws.dto;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.Role;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserSavedto {

    @NotBlank(message = "Nome requerido")
    private String name;

    @Email(message = "Email invalido")
    private String email;

    @Size(min = 4, max = 99, message = "Senha entre 4 e 99")
    private String password;

    @NotNull
    private Role role;

    private List<Request> requests = new ArrayList<Request>();

    private List<RequestStage> stages = new ArrayList<RequestStage>();


    public User transformToUser(){
        User user = new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);

        return user;
    }
}
