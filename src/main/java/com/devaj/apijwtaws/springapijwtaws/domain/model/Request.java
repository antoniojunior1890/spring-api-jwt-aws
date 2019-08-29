package com.devaj.apijwtaws.springapijwtaws.domain.model;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "request")
@NamedEntityGraph(name = "request-entity-graph", attributeNodes = {
@NamedAttributeNode("owner"), })
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String subject;

    @Column(columnDefinition = "text")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 12, nullable = false)
    private RequestState state;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Getter(onMethod = @__( @JsonIgnore))
    @OneToMany(mappedBy = "request")
    private List<RequestStage> stages = new ArrayList<RequestStage>();
}
