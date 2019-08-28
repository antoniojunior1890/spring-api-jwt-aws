package com.devaj.apijwtaws.springapijwtaws.controller;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.dto.UserLogindto;
import com.devaj.apijwtaws.springapijwtaws.service.RequestService;
import com.devaj.apijwtaws.springapijwtaws.service.RequestStageService;
import com.devaj.apijwtaws.springapijwtaws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("request-stages")
public class RequestStageController {

    @Autowired
    private RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<RequestStage> save(@RequestBody RequestStage requestStage){
        RequestStage createdRequestStage = requestStageService.save(requestStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> getById(@PathVariable("id") Long id){
        RequestStage requestStage = requestStageService.getById(id);
        return ResponseEntity.ok(requestStage);
    }

}
