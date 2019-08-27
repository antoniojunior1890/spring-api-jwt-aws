package com.devaj.apijwtaws.springapijwtaws.repository;


import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import com.devaj.apijwtaws.springapijwtaws.domain.enums.Role;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import com.devaj.apijwtaws.springapijwtaws.domain.repository.RequestRepository;
import com.devaj.apijwtaws.springapijwtaws.domain.repository.UserRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void AsaveTest(){
        User owner = new User();
        owner.setId(1L);

        Request request = new Request(null, "Novo Not DELL", "Pretendo obter not dell novo", new Date(), RequestState.OPEN, owner, null);
        Request createdRequest = requestRepository.save(request);

        assertThat(createdRequest.getId()).isEqualTo(1L);
    }

    @Test
    public void updateTest(){
        User owner = new User();
        owner.setId(1L);

        Request request = new Request(1L, "Novo Not DELL", "Pretendo obter not dell novo TOOP", null, RequestState.OPEN, owner, null);
        Request updatedRequestr = requestRepository.save(request);

        assertThat(updatedRequestr.getDescription()).isEqualTo("Pretendo obter not dell novo TOOP");
    }

    @Test
    public void getByIdTest(){
        Optional<Request> result = requestRepository.findById(1L);
        Request request = result.get();

        assertThat(request.getSubject()).isEqualTo("Novo Not DELL");
    }

    @Test
    public void listTest(){
        List<Request> requestList = requestRepository.findAll();

        assertThat(requestList.size()).isEqualTo(1);
    }

    @Test
    public void listByOwnerIdTest(){
        List<Request> requestList = requestRepository.findAllByOwnerId(1L);

        assertThat(requestList.size()).isEqualTo(1);
    }

    @Test
    public void updateStatusTest(){
        int affectedRows = requestRepository.updateStatus(1L, RequestState.OPEN);

        assertThat(affectedRows).isEqualTo(1);
    }

}
