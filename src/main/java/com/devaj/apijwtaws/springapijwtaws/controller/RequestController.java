package com.devaj.apijwtaws.springapijwtaws.controller;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageRequestModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import com.devaj.apijwtaws.springapijwtaws.dto.RequestSavedto;
import com.devaj.apijwtaws.springapijwtaws.dto.RequestUpdatedto;
import com.devaj.apijwtaws.springapijwtaws.service.RequestService;
import com.devaj.apijwtaws.springapijwtaws.service.RequestStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestStageService requestStageService;

    @PostMapping
    public ResponseEntity<Request> save(@RequestBody @Valid RequestSavedto requestSavedto){
        Request request = requestSavedto.transformToRequest();

        Request createdRequest = requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    @PreAuthorize("@accessManager.isRequestOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<Request> update(@PathVariable("id") Long id, @RequestBody @Valid RequestUpdatedto requestUpdatedto){
        Request request = requestUpdatedto.transformToRequest();

        request.setId(id);
        Request updatedRequest = requestService.update(request);
        return ResponseEntity.ok(updatedRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable("id") Long id){
        Request request = requestService.getById(id);
        return ResponseEntity.ok(request);
    }

    @GetMapping
    public ResponseEntity<PageModel<Request>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ){
        PageRequestModel pr = new PageRequestModel(page, size);

        PageModel<Request> requestPageModel = requestService.listAllOnLazyMode(pr);
        return ResponseEntity.ok(requestPageModel);
    }

    @GetMapping("/{id}/request-stages")
    public ResponseEntity<PageModel<RequestStage>> listAllStagesByRequestId(
            @PathVariable("id") Long id,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size){
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<RequestStage> requestStagePageModel = requestStageService.listAllByRequestIdOnLazyMode(id, pr);
        return ResponseEntity.ok(requestStagePageModel);
    }
}
