package br.com.challenge.payment.boundary.http.feign.dto;

import br.com.challenge.payment.core.model.User;

public record UserByIdResponseDTO(
        Integer id,
        String name,
        String cpf,
        String cnpj,
        WalletResponseDTO wallet,
        AccountResponseDTO account
) {

    public User toUserModel() {
        User user = new User();
        user.setName(this.name);
        user.setCnpj(this.cnpj);
        user.setCpf(this.cpf);
        user.setWallet(this.wallet.toModel());
        user.setEmail(this.account.email());
        return user;
    }
}
