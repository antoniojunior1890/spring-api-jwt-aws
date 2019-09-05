package com.devaj.apijwtaws.springapijwtaws.controller;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Pageable.PageRequestModel;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.dto.UserLogindto;
import com.devaj.apijwtaws.springapijwtaws.dto.UserSavedto;
import com.devaj.apijwtaws.springapijwtaws.dto.UserUpdateRoledto;
import com.devaj.apijwtaws.springapijwtaws.dto.UserUpdatedto;
import com.devaj.apijwtaws.springapijwtaws.security.JwtManager;
import com.devaj.apijwtaws.springapijwtaws.service.RequestService;
import com.devaj.apijwtaws.springapijwtaws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtManager jwtManager;

    @Secured({"ROLE_ADMINISTRATOR"})
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSavedto userSavedto){
        User user = userSavedto.transformToUser();

        User createdUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PreAuthorize("@accessManager.isOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody @Valid UserUpdatedto userUpdatedto){
        User user = userUpdatedto.transformToUser();

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
    public ResponseEntity<String> login(@RequestBody @Valid UserLogindto userLogindto){
//        User loggedUser = userService.login(userLogindto.getEmail(), userLogindto.getPassword() );
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLogindto.getEmail(), userLogindto.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        String email = userSpring.getUsername();
        List<String> roles = userSpring.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toList());

        String jwt = jwtManager.createToken(email, roles);


        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsByOwnerId(@PathVariable("id") Long id,
                                                                  @RequestParam(value = "page" , defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "5") int size){

        PageRequestModel pr = new PageRequestModel(page, size);

        PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyMode(id, pr);
        return ResponseEntity.ok(pm);
    }

    @Secured({"ROLE_ADMINISTRATOR"})
    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@RequestBody @Valid UserUpdateRoledto userRoledto,
                                        @PathVariable("id") Long id){
        User user = new User();
        user.setId(id);
        user.setRole(userRoledto.getRole());

        userService.updateRole(user);

        return ResponseEntity.ok().build();
    }
}
