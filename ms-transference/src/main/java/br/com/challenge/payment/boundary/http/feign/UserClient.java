package br.com.challenge.payment.boundary.http.feign;

import br.com.challenge.payment.boundary.http.feign.dto.UserByIdResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserFeign", url = "${external.feign.user}")
public interface UserClient {

    @GetMapping("/v1/user/{id}")
    UserByIdResponseDTO findUserByID(@PathVariable("id") Integer id);
}
