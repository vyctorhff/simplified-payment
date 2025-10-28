package br.com.challenge.payment.helpers.model;

import br.com.challenge.payment.core.model.Wallet;

public class WalletHelper {
    public static Wallet createBase() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setCurrentValue(200.0);
        return wallet;
    }
}
