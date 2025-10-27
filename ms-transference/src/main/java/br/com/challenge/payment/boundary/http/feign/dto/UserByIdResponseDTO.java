package br.com.challenge.payment.boundary.http.feign.dto;

import br.com.challenge.payment.core.model.User;

public record UserByIdResponseDTO() {

    public User toUserModel() {
        return null;
    }
}
