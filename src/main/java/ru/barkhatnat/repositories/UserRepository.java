package ru.barkhatnat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.barkhatnat.entity.User;


public interface UserRepository extends CrudRepository<User, Integer> {
}
