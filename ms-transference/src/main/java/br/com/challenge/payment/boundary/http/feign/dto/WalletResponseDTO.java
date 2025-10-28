package br.com.challenge.payment.boundary.http.feign.dto;

import br.com.challenge.payment.core.model.Wallet;

public record WalletResponseDTO(Double currentValue) {
    public Wallet toModel() {
        return new Wallet(this.currentValue);
    }
}
