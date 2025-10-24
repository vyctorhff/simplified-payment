package br.com.challenge.payment.core.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApplicationException extends RuntimeException {

    private List<String> errors;

    public ApplicationException(String message) {
        super(message);
        this.errors = new ArrayList<>();
    }

    public void add(String error) {
        this.errors.add(error);
    }
}
