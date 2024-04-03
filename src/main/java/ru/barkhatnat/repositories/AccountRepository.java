package ru.barkhatnat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.barkhatnat.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
