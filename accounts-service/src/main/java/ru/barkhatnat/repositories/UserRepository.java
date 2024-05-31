package ru.barkhatnat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.barkhatnat.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
