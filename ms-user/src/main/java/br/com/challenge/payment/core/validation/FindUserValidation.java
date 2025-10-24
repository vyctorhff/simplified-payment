package br.com.challenge.payment.core.validation;

import br.com.challenge.payment.core.exception.FindUserException;
import org.springframework.stereotype.Component;

@Component
public class FindUserValidation {

    public void findByIdValidation(Integer id) {
        if (id == null || id < 0) {
            throw new FindUserException("Invalid id: " + id, true);
        }
    }
}
