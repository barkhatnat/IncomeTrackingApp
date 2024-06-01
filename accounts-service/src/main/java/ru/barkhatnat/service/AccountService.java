package ru.barkhatnat.service;

import ru.barkhatnat.DTO.AccountDto;
import ru.barkhatnat.entity.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Iterable<Account> findAllAccounts();

    Account createAccount(AccountDto accountDto);

    Optional<Account> findAccount(int id);

    void updateAccount(Integer id, String title, BigDecimal balance);

    void deleteAccount(int id);
}
