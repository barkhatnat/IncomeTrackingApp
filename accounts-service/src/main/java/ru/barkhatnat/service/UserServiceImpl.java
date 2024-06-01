package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.DTO.UserCreateDto;
import ru.barkhatnat.DTO.UserUpdateDto;
import ru.barkhatnat.repositories.UserRepository;
import ru.barkhatnat.entity.User;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public User createUser(UserCreateDto userCreateDto) {
        String encodedPassword = passwordEncoder.encode(userCreateDto.password());
        return this.userRepository.save(new User(userCreateDto.username(), encodedPassword, userCreateDto.email(), getCreationDate(), getRole()));
    }

    @Override
    @Transactional
    public Optional<User> findUser(int id) {
        return this.userRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) {
        this.userRepository.findById(userUpdateDto.id()).ifPresentOrElse(user -> {
                    user.setUsername(userUpdateDto.username());
                    user.setPassword(userUpdateDto.password());
                    user.setEmail(userUpdateDto.email());
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

    private Timestamp getCreationDate(){
        return Timestamp.from(Instant.now());
    }

    private String getRole(){
        return "USER";
    }
}
