package com.devaj.apijwtaws.springapijwtaws.service;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.exception.NotFoundException;
import com.devaj.apijwtaws.springapijwtaws.repository.RequestRepository;
import com.devaj.apijwtaws.springapijwtaws.repository.UserRepository;
import com.devaj.apijwtaws.springapijwtaws.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public Request save(Request request){

        request.setState(RequestState.OPEN);
        request.setCreationDate(new Date());

        Request createdRequest = requestRepository.save(request);
        return createdRequest;
    }

    public Request update(Request request){
        Request updatedRequest = requestRepository.save(request);
        return updatedRequest;
    }

    public Request getById(Long id){
        Optional<Request> result = requestRepository.findById(id);
//        return result.get();
        return result.orElseThrow(()-> new NotFoundException("Não há pedido com id "+id));
    }

    public List<Request> listAll(){
        List<Request> requests = requestRepository.findAllFechOwner();
        return requests;
    }

    public List<Request> listAllByOwnerId(Long ownerId){
        List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
        return requests;
    }
}
