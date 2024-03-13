package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.dao.AccountDao;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountDao accountDao;
    private final UserServiceImpl userService; //TODO УДАЛИТЬ
    @Override
    @Transactional
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @Override
    @Transactional
    public void saveAccount(String title, BigDecimal balance) {
        User user = userService.getUser(1);
        Timestamp createdAt = Timestamp.from(Instant.now());
        accountDao.saveAccount(new Account(null, title, balance, user, createdAt));
    }

    @Override
    @Transactional
    public Account getAccount(int id) {
        return accountDao.getAccount(id);
    }

    @Override
    @Transactional
    public void deleteAccount(int id) {
        accountDao.deleteAccount(id);
    }
}
