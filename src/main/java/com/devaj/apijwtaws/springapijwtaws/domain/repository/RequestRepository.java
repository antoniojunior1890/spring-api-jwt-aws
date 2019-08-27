package com.devaj.apijwtaws.springapijwtaws.domain.repository;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByOwnerId(Long id);

    @Query("update Request r set r.state = :state where r.id = :id")
    Request updateStatus(@Param("id") Long id, @Param("state") RequestState state);
}
