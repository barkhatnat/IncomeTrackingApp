package ru.barkhatnat;

import ru.barkhatnat.dao.UserDaoImpl;

import java.util.List;

public class test1 {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        List users = userDao.getAllUsers();
        System.out.println(users);

    }
}
