package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.http.feign.UserClient;
import br.com.challenge.payment.boundary.http.feign.dto.UserByIdResponseDTO;
import br.com.challenge.payment.boundary.repository.redis.UserCacheRepository;
import br.com.challenge.payment.boundary.repository.redis.model.UserCache;
import br.com.challenge.payment.core.exception.FindUserException;
import br.com.challenge.payment.core.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindUserService {

    private final UserCacheRepository userCacheRepository;

    private final UserClient userClient;

    public User find(Integer id) {
        Optional<User> inCache = findInCache(id);
        return inCache.orElseGet(() -> findUserExternal(id));
    }

    private Optional<User> findInCache(Integer id) {
        log.info("Search user in cache");
        try {
            Optional<UserCache> userCache = userCacheRepository.findById(String.valueOf(id));
            if (userCache.isPresent()) {
                User user = userCache.get().toUserModel();
                return Optional.of(user);
            }
        } catch (Exception e) {
            log.info("User not locate in cache");
        }

        return Optional.empty();
    }

    private User findUserExternal(Integer id) throws FindUserException {
        log.info("Search user in external api");
        try {
            UserByIdResponseDTO userByID = userClient.findUserByID(id);
            return userByID.toUserModel();
        } catch (Exception e) {
            throw new FindUserException("User not locate: " + id);
        }
    }
}
