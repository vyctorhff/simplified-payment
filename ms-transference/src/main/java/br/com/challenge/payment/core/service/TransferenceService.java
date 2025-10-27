package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.repository.WalletRepository;
import br.com.challenge.payment.core.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferenceService {

    private final WalletRepository walletRepository;

    @Transactional
    public void process(Transaction transaction) {
        transaction.transfer(transaction.getValue());

        walletRepository.save(transaction.getWalletSource());
        walletRepository.save(transaction.getWalletTarget());
    }
}
