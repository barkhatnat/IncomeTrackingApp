package ru.barkhatnat.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountDaoImpl implements AccountDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Account> getAllAccounts() {
        Session session = sessionFactory.getCurrentSession();
        List<Account> accounts = session.createQuery("from Account order by id").getResultList();
        return accounts;
    }

    @Override
    public void saveAccount(Account account) {
    }

    @Override
    public Account getAccount(int id) {
        return null;
    }

    @Override
    public void deleteAccount(int id) {

    }
}
