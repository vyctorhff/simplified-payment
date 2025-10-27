package br.com.challenge.payment.boundary.repository.redis;

import br.com.challenge.payment.boundary.repository.redis.model.UserCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends CrudRepository<UserCache, String> {
}
