package ru.barkhatnat.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.barkhatnat.controller.payload.LoginRequest;
import ru.barkhatnat.controller.payload.LoginResponse;
import ru.barkhatnat.controller.payload.NewUserPayload;
import ru.barkhatnat.service.AuthRestClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthRestClient authRestClient;

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "user-creation";
    }

    @PostMapping("/registration")
    public String registration(@Valid NewUserPayload payload, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return "user-creation";
        } else {
            authRestClient.registration(payload.username(), payload.password(), payload.email());
            login(new LoginRequest(payload.email(), payload.password()), session);
            return "redirect:/accounts";
        }
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "log-in";
    }

    @PostMapping("/login")
    public String login(LoginRequest request, HttpSession session) {
        LoginResponse response = authRestClient.login(request.email(), request.password());
        session.setAttribute("accessToken", response.accessToken());
        return "redirect:/accounts";
    }
}
