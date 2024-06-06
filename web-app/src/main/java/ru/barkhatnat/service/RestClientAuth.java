package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.barkhatnat.client.BadRequestException;
import ru.barkhatnat.controller.payload.GlobalAccessToken;
import ru.barkhatnat.controller.payload.LoginRequest;
import ru.barkhatnat.controller.payload.LoginResponse;
import ru.barkhatnat.controller.payload.NewUserPayload;
import ru.barkhatnat.entity.User;


import java.util.List;
@Component
@RequiredArgsConstructor
public class RestClientAuth implements AuthRestClient {
    private final RestClient restClient;
    private final GlobalAccessToken accessToken;
    @Override
    public User registration(String username, String password, String email) {
        try {
            return this.restClient
                    .post()
                    .uri("/auth/registration")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NewUserPayload(username, password, email))
                    .retrieve()
                    .body(User.class);
        } catch (HttpClientErrorException.BadRequest e) {
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            //TODO check ProblemDetails
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public LoginResponse login(String email, String password) {
        try {
            LoginResponse loginResponse =  this.restClient
                    .post()
                    .uri("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new LoginRequest(email, password))
                    .retrieve()
                    .body(LoginResponse.class);
            accessToken.setAccessToken(loginResponse.accessToken());
            return loginResponse;
        } catch (HttpClientErrorException.BadRequest e) {
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            //TODO check ProblemDetails
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

}
