package br.com.challenge.payment.core.exception;

public class FindUserException extends ApplicationException {
    public FindUserException(String message) {
        super(message);
    }

    public FindUserException(String message, boolean isValidation) {
        super(message);
    }
}
