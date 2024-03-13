package ru.barkhatnat.service;

import ru.barkhatnat.entity.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();
    public void saveAccount(Account account);
    public Account getAccount(int id);
    public void deleteAccount(int id);
}
