package br.com.challenge.payment.boundary.http.feign.dto;

import br.com.challenge.payment.core.model.TransactionRequest;

public record PaymentRequestDTO (
        Integer payer,
        Integer payee,
        Double value
) {
    public TransactionRequest toModel() {
        return new TransactionRequest(
                payer,
                payee,
                value
        );
    }
}
