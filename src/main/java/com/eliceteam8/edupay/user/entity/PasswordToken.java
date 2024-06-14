package com.eliceteam8.edupay.user.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;



@Getter
@Setter
@RedisHash("passwordToken")
public class PasswordToken implements Serializable {

    @Id
    private String userId;
    private int attempt;
    private long block;
    private String token;

    @TimeToLive
    private long expiration;

    public PasswordToken(String userId) {
        this.userId = userId;
        this.attempt = 0;
        this.block = 0;
        this.token = "";
        this.expiration = 0;
    }



}
