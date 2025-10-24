package br.com.challenge.payment.boundary.http;

import br.com.challenge.payment.boundary.http.dto.UserResponseDTO;
import br.com.challenge.payment.core.model.User;
import br.com.challenge.payment.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable("id") Integer id) {
        User user = this.userService.findById(id);
        return UserResponseDTO.fromUser(user);
    }
}
