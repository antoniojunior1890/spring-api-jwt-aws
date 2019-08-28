package com.devaj.apijwtaws.springapijwtaws.service;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.exception.NotFoundException;
import com.devaj.apijwtaws.springapijwtaws.repository.RequestRepository;
import com.devaj.apijwtaws.springapijwtaws.repository.RequestStageRepository;
import com.devaj.apijwtaws.springapijwtaws.repository.UserRepository;
import com.devaj.apijwtaws.springapijwtaws.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestStageService {

    @Autowired
    RequestStageRepository requestStageRepository;

    @Autowired
    RequestRepository requestRepository;

    public RequestStage save(RequestStage requestStage){
        requestStage.setRealizationDate(new Date());
        RequestStage createdRequestStage = requestStageRepository.save(requestStage);

        Long requestId = requestStage.getRequest().getId();
        RequestState requestState = requestStage.getState();

        requestRepository.updateStatus(requestId, requestState);

        return createdRequestStage;
    }

    public RequestStage getById(Long id){
        Optional<RequestStage> result = requestStageRepository.findById(id);

        return result.orElseThrow(()-> new NotFoundException("Não há estado pedido com id "+id));
    }

    public List<RequestStage> listAllByResquestId(Long requestId){
        List<RequestStage> requestStageList = requestStageRepository.findAllByRequestId(requestId);
        return requestStageList;
    }

}
