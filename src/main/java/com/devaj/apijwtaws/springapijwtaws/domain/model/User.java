package com.devaj.apijwtaws.springapijwtaws.domain.model;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @Column(length = 75, nullable = false, unique = true)
    private String email;


    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Request> requests = new ArrayList<Request>();

    @OneToMany(mappedBy = "user")
    private List<RequestStage> stages = new ArrayList<RequestStage>();
}