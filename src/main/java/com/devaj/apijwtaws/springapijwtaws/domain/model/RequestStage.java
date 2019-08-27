package com.devaj.apijwtaws.springapijwtaws.domain.model;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "request_stage")
public class RequestStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "realization_date", nullable = false)
    private Date realizationDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private RequestState state;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
