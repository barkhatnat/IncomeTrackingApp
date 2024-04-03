package ru.barkhatnat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.repositories.UserRepository;
import ru.barkhatnat.entity.User;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public User createUser(String username, String password, String email) {
        Timestamp createdAt = Timestamp.from(Instant.now());
        return this.userRepository.save(new User(username, password, email, createdAt));
    }

    @Override
    @Transactional
    public Optional<User> findUser(int id) {
        return this.userRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, String username, String password, String email) {
        this.userRepository.findById(id).ifPresentOrElse(account -> {
                    account.setUsername(username);
                    account.setPassword(password);
                    account.setEmail(email);
                }, () -> {
                    throw new NoSuchElementException();
                }
        );
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
