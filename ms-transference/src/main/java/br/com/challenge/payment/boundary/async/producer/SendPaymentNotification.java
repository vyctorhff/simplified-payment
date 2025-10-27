package br.com.challenge.payment.boundary.async.producer;

import br.com.challenge.payment.boundary.async.body.SendPaymentNotificationBody;
import br.com.challenge.payment.core.model.Transaction;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendPaymentNotification {

    @Value("${async.produto.topico}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(Transaction transaction) {
        SendPaymentNotificationBody body = SendPaymentNotificationBody.fromModel(transaction);
        kafkaTemplate.send(topic, new Gson().toJson(body));
    }
}
