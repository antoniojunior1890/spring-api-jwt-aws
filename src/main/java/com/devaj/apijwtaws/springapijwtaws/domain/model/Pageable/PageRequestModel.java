package com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageRequestModel {
    private int page;
    private int size;
}
