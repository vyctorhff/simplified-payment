package br.com.challenge.payment.core.model;

import br.com.challenge.payment.core.exception.PaymentValidatorException;

public record TransactionRequest(
        Integer payer,
        Integer payee,
        Double value
) {
    public void validate() throws PaymentValidatorException {
        if (value <= 0.0) {
            throw new PaymentValidatorException("Transfer value invalid");
        }

        if (isValidId(payer)) {
            throw new PaymentValidatorException("User payer id invalid");
        }

        if (isValidId(payee)) {
            throw new PaymentValidatorException("User payee id invalid");
        }
    }

    private boolean isValidId(Integer id) {
        return payee == null || payee <= 0;
    }
}
