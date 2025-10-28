package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.http.feign.ExternalAuthorizationClient;
import br.com.challenge.payment.core.model.StatusTransaction;
import br.com.challenge.payment.core.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessPaymentPreCondictionsService {

    private final ExternalAuthorizationClient externalAuthorizationClient;

    public void check(Transaction transaction) {
        checkUserSourceBalance(transaction);
        checkUserSourceType(transaction);
        checkAuthorized(transaction);
    }

    private void checkUserSourceBalance(Transaction transaction) {
        log.info("Validating user source balance");

        if (transaction.isUserSourceHasBalance()) {
            transaction.setStatus(StatusTransaction.CANCELED_OVER_BALANCE);
        }
    }

    private void checkUserSourceType(Transaction transaction) {
        log.info("Validating user source type");

        if (transaction.isUserSourceNotLojist()) {
            transaction.setStatus(StatusTransaction.CANCELED_USER_COMMON);
        }
    }

    private void checkAuthorized(Transaction transaction) {
        log.info("Validating external authorization");

        boolean isAuthorized = externalAuthorizationClient.process().isAuthorized();
        if (isAuthorized) {
            log.info("NOT AUTHORIZED!!!!!");
            transaction.setStatus(StatusTransaction.CANCELED_NOT_UNAUTHORIZED);
        }
    }
}
