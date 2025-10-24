package br.com.challenge.payment.boundary.http.dto;

import br.com.challenge.payment.core.model.Wallet;

public record WalletResponseDTO(Double currentValue) {
    public static WalletResponseDTO fromWallet(Wallet wallet) {
        return new WalletResponseDTO(wallet.getCurrentValue());
    }
}
