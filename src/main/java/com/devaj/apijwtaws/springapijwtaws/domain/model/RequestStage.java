package com.devaj.apijwtaws.springapijwtaws.domain.model;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestStage {
    private Long id;
    private String description;
    private Date realizationDate;
    private RequestState state;
    private Request request;
    private User user;
}
