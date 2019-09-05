package com.devaj.apijwtaws.springapijwtaws.security;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.exception.NotFoundException;
import com.devaj.apijwtaws.springapijwtaws.repository.UserRepository;
import com.devaj.apijwtaws.springapijwtaws.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("accessManager")
public class AccessManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestService requestService;

    public boolean isOwner(Long id){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> result = userRepository.findByEmail(email);

        if(!result.isPresent()) throw new NotFoundException("Não existe usuario cadastrado com este email: "+ email);

        User user = result.get();

        return user.getId() == id;
    }

    public boolean isRequestOwner(Long id){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> result = userRepository.findByEmail(email);

        if(!result.isPresent()) throw new NotFoundException("Não existe usuario cadastrado com este email: "+ email);

        User user = result.get();

        Request request = requestService.getById(id);

        return user.getId() == request.getOwner().getId();
    }
}
