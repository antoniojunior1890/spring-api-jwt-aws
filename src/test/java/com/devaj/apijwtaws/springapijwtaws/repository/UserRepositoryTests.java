package com.devaj.apijwtaws.springapijwtaws.repository;


import com.devaj.apijwtaws.springapijwtaws.domain.enums.Role;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.domain.repository.UserRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void AsaveTest(){
        User user = new User(null, "Antonio", "antonio@email.com", "1234", Role.ADMINISTRATOR, null, null);
        User createdUser = userRepository.save(user);

        assertThat(createdUser.getId()).isEqualTo(1L);
    }

    @Test
    public void updateTest(){
        User user = new User(1L, "Junior", "antonio@email.com", "1234", Role.ADMINISTRATOR, null, null);
        User updatedUser = userRepository.save(user);

        assertThat(updatedUser.getName()).isEqualTo("Junior");
    }

    @Test
    public void getByIdTest(){
        Optional<User> result = userRepository.findById(1L);
        User user = result.get();

        assertThat(user.getPassword()).isEqualTo("1234");
    }

    @Test
    public void listTest(){
        List<User> userList = userRepository.findAll();

        assertThat(userList.size()).isEqualTo(1);
    }

    @Test
    public void loginTest(){
        Optional<User> result = userRepository.login("antonio@email.com", "1234");
        User loggedUser = result.get();

        assertThat(loggedUser.getId()).isEqualTo(1L);
    }
}
