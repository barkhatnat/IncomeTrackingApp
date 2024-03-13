package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.dao.AccountDao;
import ru.barkhatnat.entity.Account;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountDao accountDao;
    @Override
    @Transactional
    public List<Account> getAllAccounts() {
        return accountDao.getAllAccounts();
    }

    @Override
    @Transactional
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
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
