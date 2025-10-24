package br.com.challenge.payment.helpers.model;

import br.com.challenge.payment.core.model.Account;
import br.com.challenge.payment.core.model.User;
import br.com.challenge.payment.core.model.Wallet;

public class UserHelper {

    public static User createBase() {
        var user = new User();
        user.setNameFirst("fist name");
        user.setNameLast("last name");

        user.setAccount(new Account());
        user.getAccount().setEmail("email@email.com");
        user.getAccount().setPassword("1231");

        user.setWallet(new Wallet());
        user.getWallet().setCurrentValue(100.0);

        return user;
    }

    public static User createUserCommon() {
        var user = createBase();
        user.setCpf("00000000001");
        return user;
    }

    public static User createUserLojist() {
        var user = createBase();
        user.setCnpj("00000000000001");
        return user;
    }
}
