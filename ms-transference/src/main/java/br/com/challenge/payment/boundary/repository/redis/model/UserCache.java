package br.com.challenge.payment.boundary.repository.redis.model;

import br.com.challenge.payment.core.model.User;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash
public class UserCache implements Serializable {

    private String id;
    private String name;

    public User toUserModel() {
        return null;
    }
}
