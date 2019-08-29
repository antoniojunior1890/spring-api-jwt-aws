package com.devaj.apijwtaws.springapijwtaws.dto;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestSavedto {

    @NotBlank(message = "Assunto requerido")
    private String subject;
    private String description;

    @NotNull(message = "Usuario requerido")
    private User owner;
    private List<RequestStage> stages = new ArrayList<RequestStage>();

    public Request transformToRequest(){
        Request request = new Request(null, this.subject, this.description, null, null, this.owner, this.stages);

        return request;
    }
}
