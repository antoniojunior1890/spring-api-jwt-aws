package com.devaj.apijwtaws.springapijwtaws.controller;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageRequestModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.dto.UserLogindto;
import com.devaj.apijwtaws.springapijwtaws.dto.UserUpdateRoledto;
import com.devaj.apijwtaws.springapijwtaws.service.RequestService;
import com.devaj.apijwtaws.springapijwtaws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user){
        user.setId(id);
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> listAll(
                        @RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "size", defaultValue = "5") int size
    ){
        PageRequestModel pr = new PageRequestModel(page, size);

        PageModel<User> userPageModel = userService.listAllOnLazyMode(pr);
        return ResponseEntity.ok(userPageModel);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogindto userLogindto){
        User loggedUser = userService.login(userLogindto.getEmail(), userLogindto.getPassword() );
        return ResponseEntity.ok(loggedUser);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsByOwnerId(@PathVariable("id") Long id,
                                                                  @RequestParam(value = "page" , defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "5") int size){

        PageRequestModel pr = new PageRequestModel(page, size);

        PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyMode(id, pr);
        return ResponseEntity.ok(pm);
    }

    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@RequestBody UserUpdateRoledto userRoledto,
                                        @PathVariable("id") Long id){
        User user = new User();
        user.setId(id);
        user.setRole(userRoledto.getRole());

        userService.updateRole(user);

        return ResponseEntity.ok().build();
    }
}
