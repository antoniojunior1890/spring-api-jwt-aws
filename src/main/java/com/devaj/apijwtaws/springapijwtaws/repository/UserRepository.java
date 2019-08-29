package com.devaj.apijwtaws.springapijwtaws.repository;

import com.devaj.apijwtaws.springapijwtaws.domain.enums.Role;
import com.devaj.apijwtaws.springapijwtaws.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from user u where u.email = :email and u.password = :password")
    Optional<User> login(@Param("email")String email, @Param("password") String password);

//    @Query(value = "select u from user u", countQuery = "select count (u) from user u")
//    Page<User> findAllOnLazyMode(Pageable pageable);

    @Modifying
    @Transactional(readOnly = false)
    @Query("update user u set u.role = :role where u.id = :id")
    int updateRole(@Param("id") Long id, @Param("role") Role role);
}
