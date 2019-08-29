package com.devaj.apijwtaws.springapijwtaws.service;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageRequestModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.exception.NotFoundException;
import com.devaj.apijwtaws.springapijwtaws.repository.UserRepository;
import com.devaj.apijwtaws.springapijwtaws.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){

        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);

        User createdUser = userRepository.save(user);
        return createdUser;
    }

    public User update(User user){

        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);

        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    public User getById(Long id){
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow(()-> new NotFoundException("Não há usuário com id "+id));
    }

    public List<User> listAll(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public PageModel<User> listAllOnLazyMode(PageRequestModel pr){
        Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
        Page<User> page = userRepository.findAll(pageable);

        PageModel<User> pageModel = new PageModel<>((int)page.getTotalElements(),
                page.getSize(),
                page.getTotalPages(),
                page.getContent());


        return pageModel;
    }

    public User login(String email, String password){
        password = HashUtil.getSecureHash(password);

        Optional<User> result = userRepository.login(email, password);
        return result.get();
    }

    public int updateRole(User user){
        return userRepository.updateRole(user.getId(), user.getRole());
    }
}
