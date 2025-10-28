package br.com.challenge.payment.helpers.model;

import br.com.challenge.payment.core.model.User;

public class UserHelper {
    public static User createBase() {
        User user = new User();
        user.setId(2);
        user.setName("Name");
        user.setEmail("email@email.com");
        user.setCpf("0101023");
        user.setCnpj("123123123");
        user.setWallet(WalletHelper.createBase());
        return user;
    }

    public static User createUserCommon() {
        User user = createBase();
        user.setCnpj(null);
        return user;
    }

    public static User createUserCommon(int id) {
        User user = createBase();
        user.setId(id);
        user.setCnpj(null);
        return user;
    }
}
