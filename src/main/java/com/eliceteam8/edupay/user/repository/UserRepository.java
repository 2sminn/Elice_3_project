package com.eliceteam8.edupay.user.repository;

import com.eliceteam8.edupay.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {



    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = {"roles", "academy"})
    Optional<User> findByEmail(String email);

    Optional<User> findUserByEmail(String email);

}
