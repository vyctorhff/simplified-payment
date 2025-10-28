package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.repository.TransactionRepository;
import br.com.challenge.payment.core.exception.PaymentException;
import br.com.challenge.payment.core.model.StatusTransaction;
import br.com.challenge.payment.core.model.Transaction;
import br.com.challenge.payment.core.model.TransactionRequest;
import br.com.challenge.payment.core.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessPaymentService {

    private final FindUserService findUserService;

    private final ProcessPaymentPreCondictionsService preCondictionsService;

    private final ProcessPaymentPostConditionsService postConditionsService;

    private final TransferenceService transferenceService;

    private final TransactionRepository repository;

    public Transaction process(TransactionRequest request) throws PaymentException {
        log.info("Validating request");
        request.validate();

        Transaction transaction = createTransaction(request);

        preCondictionsService.check(transaction);

        if (transaction.isStatusPaid()) {
            log.info("Executing transference");

            transferenceService.process(transaction);
            postConditionsService.process(transaction);
        } else {
            log.info("Transference not execute. Persisting for history");
            repository.save(transaction);
        }

        return transaction;
    }

    private Transaction createTransaction(TransactionRequest request) {
        User userSource = findUserService.find(request.payee());
        User userTarget = findUserService.find(request.payer());

        Transaction transaction = new Transaction(request.value(), userSource, userTarget);
        transaction.setStatus(StatusTransaction.PAID);

        return transaction;
    }
}
