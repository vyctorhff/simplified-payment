package br.com.challenge.payment.boundary.http.feign;

import br.com.challenge.payment.boundary.http.feign.dto.UserByIdResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "UserFeign", url = "${external.feign.user}")
public interface UserClient {

    @GetMapping
    UserByIdResponseDTO findUserByID(Integer id);
}
