package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.DTO.UserResponseDto;
import ru.barkhatnat.DTO.UserUpdateDto;
import ru.barkhatnat.entity.User;
import ru.barkhatnat.service.UserService;
import ru.barkhatnat.utils.SecurityUtil;
import ru.barkhatnat.utils.UserMapper;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("home")
public class UserRestController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUser() {
        Integer id = SecurityUtil.getCurrentUserDetails().getUserId();
        Optional<User> user = userService.findUser(id);
        return user.map(value -> ResponseEntity.ok(userMapper.toUserResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping
    public ResponseEntity<UserResponseDto> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.userService.updateUser(userUpdateDto);
            return ResponseEntity.noContent().build();
        }
    }
}
