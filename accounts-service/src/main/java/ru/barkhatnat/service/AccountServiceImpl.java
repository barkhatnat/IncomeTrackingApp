package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.repositories.AccountRepository;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserServiceImpl userService; //TODO удалить после реализации юзеров

    @Override
    @Transactional
    public Iterable<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account createAccount(String title, BigDecimal balance) {
        User user = userService.findUser(1).orElse(null); //TODO сделать соединение юзера и аккаунтов
        Timestamp createdAt = Timestamp.from(Instant.now());
        return this.accountRepository.save(new Account(null, title, balance, user, createdAt));
    }

    @Override
    @Transactional
    public Optional<Account> findAccount(int id) {
        return this.accountRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateAccount(Integer id, String title, BigDecimal balance) {
        this.accountRepository.findById(id).ifPresentOrElse(account -> {
                    account.setTitle(title);
                    account.setBalance(balance);
                }, () -> {
                    throw new NoSuchElementException();
                }
        );
    }

    @Override
    @Transactional
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }
}
