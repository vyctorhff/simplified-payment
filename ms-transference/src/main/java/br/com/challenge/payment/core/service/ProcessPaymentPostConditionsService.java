package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.async.producer.SendPaymentNotification;
import br.com.challenge.payment.core.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProcessPaymentPostConditionsService {

    private final SendPaymentNotification sendPaymentNotification;

    public void process(Transaction transaction) {
        log.info("Sending message to process notification");
        sendPaymentNotification.send(transaction);
    }
}
