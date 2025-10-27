package br.com.challenge.payment.core.model;

import br.com.challenge.payment.core.exception.PaymentValidatorException;

public record TransactionRequest(
        Integer payer,
        Integer payee,
        Double value
) {
    public void validate() {
        throw new PaymentValidatorException("not implement");
    }
}
