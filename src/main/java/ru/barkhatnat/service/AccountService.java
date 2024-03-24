package ru.barkhatnat.service;

import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    public List<Account> getAllAccounts();
    public void saveAccount(String title, BigDecimal balance);
    public void saveAccount(Integer id, String title, BigDecimal balance);
    public Account getAccount(int id);
    public void deleteAccount(int id);
}
