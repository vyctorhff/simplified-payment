package br.com.challenge.payment.boundary.http;

import br.com.challenge.payment.boundary.http.feign.dto.PaymentRequestDTO;
import br.com.challenge.payment.core.model.Transaction;
import br.com.challenge.payment.core.model.TransactionRequest;
import br.com.challenge.payment.core.service.ProcessPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class PaymentController {

    private final ProcessPaymentService processPaymentService;

    @PostMapping
    public ResponseEntity<Void> transfer(@RequestBody PaymentRequestDTO requestDTO) {
        TransactionRequest request = requestDTO.toModel();
        Transaction transaction = processPaymentService.process(request);

        // TODO: !!!!!
        return null;
    }
}
