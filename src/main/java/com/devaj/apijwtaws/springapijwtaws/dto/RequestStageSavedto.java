package com.devaj.apijwtaws.springapijwtaws.dto;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
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
public class RequestStageSavedto {

    private String description;

    @NotNull(message = "state requerido")
    private RequestState state;

    @NotNull(message = "request requerido")
    private Request request;

    @NotNull(message = "Owner requerido")
    private User owner;

    public RequestStage transformToRequestStage(){
        RequestStage requestStage = new RequestStage(null, this.description, null, this.state, this.request,this.owner);

        return requestStage;
    }
}
