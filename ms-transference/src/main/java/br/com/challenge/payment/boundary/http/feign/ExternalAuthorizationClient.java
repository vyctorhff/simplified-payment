package br.com.challenge.payment.boundary.http.feign;

import br.com.challenge.payment.boundary.http.feign.dto.AuthorizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AuthorizationFeign", url = "${external.feign.authorization}")
public interface ExternalAuthorizationClient {

    @GetMapping
    AuthorizationResponseDTO doAuthorization();
}
