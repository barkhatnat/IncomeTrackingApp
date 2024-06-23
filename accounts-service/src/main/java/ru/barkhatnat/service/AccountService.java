package ru.barkhatnat.service;

import ru.barkhatnat.DTO.AccountDto;
import ru.barkhatnat.DTO.AccountResponseDto;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.Operation;

import java.math.BigDecimal;
import java.util.Optional;

public interface AccountService {
    Iterable<Account> findAllAccounts();

    Iterable<Operation> findAllAccountOperations(Account account);

    AccountResponseDto createAccount(AccountDto accountDto);

    Optional<Account> findAccount(int id);

    void updateAccount(Integer id, String title, BigDecimal balance);

    void deleteAccount(int id);
}
