package br.com.challenge.payment.helpers.model;

import br.com.challenge.payment.core.model.StatusTransaction;
import br.com.challenge.payment.core.model.Transaction;

import java.time.LocalDateTime;

public class TransactionHelper {

    public static Transaction createBase() {
        Transaction transaction = new Transaction(
                2000.0,
                UserHelper.createBase(),
                UserHelper.createBase()
        );
        transaction.setStatus(StatusTransaction.PAID);
        transaction.setDtCreate(LocalDateTime.now());
        transaction.setId(2);
        return transaction;
    }
}
