package com.devaj.apijwtaws.springapijwtaws.repository;

import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.RequestStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

    @Query("select rs from request_stage rs " +
            "join fetch rs.request " +
            "join fetch rs.owner where rs.request.id = :id")
    List<RequestStage> findAllByRequestId(@Param("id") Long id);


    @EntityGraph(attributePaths = {"request","owner"})
    Page<RequestStage> findAllByRequestId(Long id, Pageable pageable);
}
