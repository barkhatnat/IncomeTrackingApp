package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.DTO.UserCreateDto;
import ru.barkhatnat.DTO.UserResponseDto;
import ru.barkhatnat.DTO.UserUpdateDto;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.Category;
import ru.barkhatnat.exception.UserAlreadyExistsException;
import ru.barkhatnat.repositories.UserRepository;
import ru.barkhatnat.entity.User;
import ru.barkhatnat.utils.UserMapper;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public Iterable<Account> findAllUserAccounts(Integer userId) {
        Optional<User> user = findUser(userId);
        if (user.isEmpty()) {
            throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
        }
        return user.get().getAccounts();
    }

    @Override
    public Iterable<Category> findAllUserCategories(Integer userId) {
        Optional<User> user = findUser(userId);
        if (user.isEmpty()) {
            throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
        }
        return user.get().getCategories();
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserCreateDto userCreateDto) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(userCreateDto.email()).isPresent()) {
            throw new UserAlreadyExistsException("User with username " + userCreateDto.username() + " already exists");
        }
        String encodedPassword = passwordEncoder.encode(userCreateDto.password());
        User user = userRepository.save(new User(userCreateDto.username(), encodedPassword, userCreateDto.email(), getCreationDate(), getRole()));
        return userMapper.toUserResponse(user);
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

    private Timestamp getCreationDate() {
        return Timestamp.from(Instant.now());
    }

    private String getRole() {
        return "USER";
    }

}
