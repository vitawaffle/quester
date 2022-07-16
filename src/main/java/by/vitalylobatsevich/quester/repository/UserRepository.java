package by.vitalylobatsevich.quester.repository;

import org.springframework.stereotype.Repository;
import io.vavr.control.Option;

import by.vitalylobatsevich.quester.entity.User;

@Repository
public interface UserRepository extends AppRepository<User, Long> {

    Option<User> findByUsername(String username);

}
