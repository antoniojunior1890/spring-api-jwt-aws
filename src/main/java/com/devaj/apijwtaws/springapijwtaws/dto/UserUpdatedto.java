package com.devaj.apijwtaws.springapijwtaws.dto;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserUpdatedto {

    @NotBlank(message = "Nome requerido")
    private String name;

    @Email(message = "Email invalido")
    private String email;

    @Size(min = 4, max = 99, message = "Senha entre 4 e 99")
    private String password;

    private List<Request> requests = new ArrayList<Request>();

    private List<RequestStage> stages = new ArrayList<RequestStage>();

    public User transformToUser(){
        User user = new User(null, this.name, this.email, this.password, null, this.requests, this.stages);

        return user;
    }
}
