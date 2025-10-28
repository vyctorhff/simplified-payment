package br.com.challenge.payment.boundary.http.dto;

import br.com.challenge.payment.core.model.User;

public record UserResponseDTO(
        Integer id,
        String name,
        String cpf,
        String cnpj,
        WalletResponseDTO wallet,
        AccountResponseDTO account
) {
    public static UserResponseDTO fromUser(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getCompleteName(),
                user.getCpf(),
                user.getCnpj(),
                WalletResponseDTO.fromWallet(user.getWallet()),
                AccountResponseDTO.fromAccount(user.getAccount())
        );
    }
}
