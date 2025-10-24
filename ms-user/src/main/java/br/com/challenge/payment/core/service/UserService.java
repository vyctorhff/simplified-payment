package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.repository.UserRepository;
import br.com.challenge.payment.core.exception.FindUserException;
import br.com.challenge.payment.core.model.User;
import br.com.challenge.payment.core.validation.FindUserValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final FindUserValidation validation;

    private final UserRepository userRepository;

    public User findById(Integer id) {
        log.info("Find user by id");
        this.validation.findByIdValidation(id);
        return this.userRepository.findById(id)
                .orElseThrow(() -> new FindUserException("Fail to find by id"));
    }
}
