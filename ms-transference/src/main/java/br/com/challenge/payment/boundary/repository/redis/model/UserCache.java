package br.com.challenge.payment.boundary.repository.redis.model;

import br.com.challenge.payment.core.model.User;
import br.com.challenge.payment.core.model.Wallet;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("users")
@Data
public class UserCache implements Serializable {

    @Id
    private String id;

    private String name;

    private String email;

    private String cpf;

    private String cnpj;

    private Double currentValue;

    public static UserCache fromUser(User user) {
        UserCache userCache = new UserCache();

        userCache.setId(user.getId().toString());
        userCache.setName(user.getName());
        userCache.setEmail(user.getEmail());
        userCache.setCnpj(user.getCnpj());
        userCache.setCpf(user.getCpf());
        userCache.setCurrentValue(user.getWallet().getCurrentValue());

        return userCache;
    }

    public User toUserModel() {
        User user = new User();

        user.setId(Integer.parseInt(this.id));
        user.setName(this.name);
        user.setCpf(this.cpf);
        user.setCnpj(this.cnpj);
        user.setEmail(this.email);

        user.setWallet(new Wallet());
        user.getWallet().setCurrentValue(this.currentValue);

        return user;
    }
}
