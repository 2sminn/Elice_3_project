package com.eliceteam8.edupay.user.repository;

import com.eliceteam8.edupay.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"roles", "academy"})
    Optional<User> findByEmail(String email);


    Optional<User> findUserByEmailAndUsername(String email, String username);

    @EntityGraph(attributePaths = {"roles", "academy"})
    Optional<User> findById(Long id);

    Optional<User> findUserByEmail(String email);

}
