package com.eliceteam8.edupay.user.repository;

import com.eliceteam8.edupay.user.entity.PasswordToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends CrudRepository<PasswordToken, String> {
}
