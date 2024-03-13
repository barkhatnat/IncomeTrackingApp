package ru.barkhatnat.service;

import ru.barkhatnat.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();
    public void saveAccount(String title, BigDecimal balance);
    public Account getAccount(int id);
    public void deleteAccount(int id);
}
