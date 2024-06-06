package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.DTO.AccountDto;
import ru.barkhatnat.repositories.AccountRepository;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.User;
import ru.barkhatnat.utils.SecurityUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserServiceImpl userService;

    @Override
    @Transactional
    public Iterable<Account> findAllAccounts() {
        return userService.findAllUserAccounts(getUser());
    }

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto) {
        return accountRepository.save(new Account(accountDto.title(), accountDto.balance(), getUser(), getCreationDate()));
    }

    @Override
    @Transactional
    public Optional<Account> findAccount(int id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateAccount(Integer id, String title, BigDecimal balance) {
        accountRepository.findById(id).ifPresentOrElse(account -> {
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

    private Timestamp getCreationDate() {
        return Timestamp.from(Instant.now());
    }

    private User getUser() {
        Integer id = SecurityUtil.getCurrentUserDetails().getUserId();
        Optional<User> user = userService.findUser(id);
        if (user.isEmpty()) {
            throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
        }
        return user.get();
    }
}
