package br.com.challenge.payment.boundary.http.feign.dto;

public record AuthorizationResponseDTO(
        String status,
        AuthorizationDataResponseDTO data
) {
    public boolean isAuthorized() {
        return data.authorization();
    }
}
