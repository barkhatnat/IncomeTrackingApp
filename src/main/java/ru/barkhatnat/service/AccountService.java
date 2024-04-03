package ru.barkhatnat.service;

import ru.barkhatnat.entity.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Iterable<Account> findAllAccounts();
    Account createAccount(String title, BigDecimal balance);
    Optional<Account> findAccount(int id);
    void updateAccount(Integer id, String title, BigDecimal balance);
    void deleteAccount(int id);
}
