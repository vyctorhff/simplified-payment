package br.com.challenge.payment.boundary.async.body;

import br.com.challenge.payment.core.model.Transaction;

import java.time.LocalDateTime;

public record SendPaymentNotificationBody (
        String email,
        String nameUserSource,
        Double value,
        LocalDateTime date
) {
    public static SendPaymentNotificationBody fromModel(Transaction transaction) {
        return new SendPaymentNotificationBody(
                transaction.getUserSource().getEmail(),
                transaction.getUserSource().getName(),
                transaction.getValue(),
                transaction.getDtCreate()
        );
    }
}
