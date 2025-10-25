package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.repository.TransactionRepository;
import br.com.challenge.payment.core.model.Transaction;
import br.com.challenge.payment.core.model.User;
import br.com.challenge.payment.core.validator.PaymentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessPaymentService {

    private final PaymentValidator validator;

    private final UserBalanceValidator userBalanceValidator;

    private final UserTypeValidator userTypeValidator;

    private final FindUserCache findUserCache;

    private final ExternalAuthorization externalAuthorization;

//    private final TransactionRepository repository;

    private final TransaferenceService transaferenceService;

    private final SendPaymentNotification sendPaymentNotification;

    public void process(Transaction transaction) {
        log.info("Processing payment");

        log.info("Validating request");
        validator.validate(transaction);

        log.info("Search users");
        findUserCache.find(transaction);

        log.info("Check if user can pay");
        userTypeValidator.validate(transaction.getUserSource());

        log.info("Check user source balance");
        userBalanceValidator.validate(transaction.getUserSource());

        log.info("Check authorization");
        externalAuthorization.authorize();

        // TODO: do the transference
//        repository.save(transaction);

        log.info("Sending message to process notification");
        sendPaymentNotification.send(transaction);
    }
}
