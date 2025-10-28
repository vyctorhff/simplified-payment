package br.com.challenge.payment.core.model;

import br.com.challenge.payment.helpers.model.TransactionHelper;
import br.com.challenge.payment.helpers.model.UserHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    void before() {
        this.transaction = TransactionHelper.createBase();
    }

    @Test
    void shouldIsStatusPaidSuccessfully() {
        transaction.setStatus(StatusTransaction.PAID);
        assertTrue(transaction.isStatusPaid());
    }

    @Test
    void shouldTransferSuccessfully() {
        Wallet walletSource = transaction.getUserSourceModel().getWallet();
        Wallet walletTarget = transaction.getUserTargetModel().getWallet();

        Double currentValueSourceBefore = walletSource.getCurrentValue();
        Double currentValueTargetBefore = walletTarget.getCurrentValue();

        transaction.setValue(200.0);
        Double valueTransfer = transaction.getValue();

        transaction.transfer();

        assertEquals(currentValueSourceBefore - valueTransfer, walletSource.getCurrentValue());
        assertEquals(currentValueTargetBefore + valueTransfer, walletTarget.getCurrentValue());
    }

    @Test
    void shouldIsUserSourceHasBalance() {
        transaction.setValue(200.0);
        assertTrue(transaction.isUserSourceHasBalance());
    }

    @Test
    void shouldIsUserSourceNotLojist() {
        transaction.setUserSourceModel(UserHelper.createUserCommon());
        assertTrue(transaction.isUserSourceNotLojist());
    }
}