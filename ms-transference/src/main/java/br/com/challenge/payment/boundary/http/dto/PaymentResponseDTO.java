package br.com.challenge.payment.boundary.http.dto;

import br.com.challenge.payment.core.model.Transaction;

import java.time.LocalDateTime;

public record PaymentResponseDTO(
        String status,
        String transactionHash,
        LocalDateTime data
) {
    public static PaymentResponseDTO fromTransaction(Transaction transaction) {
        return new PaymentResponseDTO(
                transaction.getStatus().toString(),
                transaction.getTransactionHash(),
                transaction.getDtCreate()
        );
    }
}
