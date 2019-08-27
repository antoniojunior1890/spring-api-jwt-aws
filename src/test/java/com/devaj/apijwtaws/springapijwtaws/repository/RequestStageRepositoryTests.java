package com.devaj.apijwtaws.springapijwtaws.repository;


import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import com.devaj.apijwtaws.springapijwtaws.domain.enums.Role;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
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
public class RequestStageRepositoryTests {

    @Autowired
    private RequestStageRepository requestStageRepository;

    @Test
    public void AsaveTest(){

        User owner = new User();
        owner.setId(1L);

        Request request = new Request();
        request.setId(1L);

        RequestStage requestStage = new RequestStage(null,
                "Foi comprado um novo not da dell",
                new Date(),
                RequestState.CLOSED,
                request,
                owner
                );

        RequestStage createdRequestStager = requestStageRepository.save(requestStage);

        assertThat(createdRequestStager.getId()).isEqualTo(1L);
    }

    @Test
    public void getByIdTest(){
        Optional<RequestStage> result = requestStageRepository.findById(1L);
        RequestStage requestStage = result.get();

        assertThat(requestStage.getDescription()).isEqualTo("Foi comprado um novo not da dell");
    }

    @Test
    public void listTest(){
        List<RequestStage> requestStageList = requestStageRepository.findAll();

        assertThat(requestStageList.size()).isEqualTo(1);
    }

    @Test
    public void listByResquestStageIdTest(){
        List<RequestStage> requestStageList = requestStageRepository.findAllByRequestId(1L);

        assertThat(requestStageList.size()).isEqualTo(1);
    }

}
