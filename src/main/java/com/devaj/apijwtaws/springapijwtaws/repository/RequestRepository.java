package com.devaj.apijwtaws.springapijwtaws.repository;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.RequestState;
import com.devaj.apijwtaws.springapijwtaws.domain.model.Request;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("select r from request r join fetch r.owner where r.owner.id = :id")
    List<Request> findAllByOwnerId(@Param("id") Long id);

//    @Query(value = "select r from request r inner join fetch r.owner where r.owner.id = :id",
//            countQuery = "select count (r) from request r inner join r.owner where r.owner.id = :id")
//    Page<Request> findAllByOwnerId(@Param("id") Long id, Pageable pageable);
    @EntityGraph(attributePaths = {"owner"})
    Page<Request> findAllByOwnerId(Long id, Pageable pageable);

    @Modifying
    @Transactional(readOnly = false)
    @Query("update request r set r.state = :state where r.id = :id")
    int updateStatus(@Param("id") Long id, @Param("state") RequestState state);

    @Query("select r from request r join fetch r.owner")
    List<Request> findAllFechOwner();

}
