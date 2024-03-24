package ru.barkhatnat.dao;

import ru.barkhatnat.entity.Account;

import java.util.List;

public interface AccountDao {
    public List<Account> getAllAccounts();
    public void saveAccount(Account account);

    void updateAccount(Account account);

    public Account getAccount(int id);
    public void deleteAccount(int id);
}
