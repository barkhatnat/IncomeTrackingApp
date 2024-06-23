package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.DTO.AccountDto;
import ru.barkhatnat.DTO.AccountResponseDto;
import ru.barkhatnat.entity.Operation;
import ru.barkhatnat.repositories.AccountRepository;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.utils.AccountMapper;
import ru.barkhatnat.utils.CurrentUser;
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
    private final CurrentUser currentUser;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public Iterable<Account> findAllAccounts() {
        Integer id = SecurityUtil.getCurrentUserDetails().getUserId();
        return userService.findAllUserAccounts(id);
    }

    @Override
    public Iterable<Operation> findAllAccountOperations(Account account) {
        return account.getOperations();
    }

    @Override
    @Transactional
    public AccountResponseDto createAccount(AccountDto accountDto) {
        Account account = accountRepository.save(new Account(accountDto.title(), accountDto.balance(), currentUser.getUser(), getCreationDate()));
        return accountMapper.toAccountResponseDto(account);
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
                    if (account.getUser() != null && account.getUser().getId().equals(SecurityUtil.getCurrentUserDetails().getUserId())) {
                        account.setTitle(title);
                        account.setBalance(balance);
                    } else {
                        throw new IllegalArgumentException("You do not have permission to update this account.");
                    }
                }, () -> {
                    throw new NoSuchElementException();
                }
        );
    }

    @Override
    @Transactional
    public void deleteAccount(int id) {
        accountRepository.findById(id).ifPresentOrElse(account -> {
                    if (account.getUser() != null && account.getUser().getId().equals(SecurityUtil.getCurrentUserDetails().getUserId())) {
                        accountRepository.deleteById(id);
                    } else {
                        throw new IllegalArgumentException("You do not have permission to delete this account.");
                    }
                }, () -> {
                    throw new NoSuchElementException();
                }
        );
    }

    private Timestamp getCreationDate() {
        return Timestamp.from(Instant.now());
    }
}
