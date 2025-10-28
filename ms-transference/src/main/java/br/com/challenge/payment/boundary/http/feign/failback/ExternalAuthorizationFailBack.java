package br.com.challenge.payment.boundary.http.feign.failback;

import br.com.challenge.payment.boundary.http.feign.ExternalAuthorizationClient;
import br.com.challenge.payment.boundary.http.feign.dto.AuthorizationDataResponseDTO;
import br.com.challenge.payment.boundary.http.feign.dto.AuthorizationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ExternalAuthorizationFailBack implements ExternalAuthorizationClient {
    @Override
    public AuthorizationResponseDTO process() {
        return new AuthorizationResponseDTO(
                "not authorized",
                new AuthorizationDataResponseDTO(false)
        );
    }
}
