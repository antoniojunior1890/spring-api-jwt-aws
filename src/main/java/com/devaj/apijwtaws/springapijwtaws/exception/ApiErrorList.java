package com.devaj.apijwtaws.springapijwtaws.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ApiErrorList extends ApiError{

    private static final long serialVersionUID = 1L;

    private List<String> errors;

    public ApiErrorList(int code, String msg, Date date, List<String> errors) {
        super(code, msg, date);
        this.errors = errors;
    }
}
