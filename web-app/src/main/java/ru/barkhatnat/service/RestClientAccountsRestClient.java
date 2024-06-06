package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.barkhatnat.client.BadRequestException;
import ru.barkhatnat.controller.payload.NewAccountPayload;
import ru.barkhatnat.controller.payload.UpdateAccountPayload;
import ru.barkhatnat.entity.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
public class RestClientAccountsRestClient implements AccountRestClient {
    private static final ParameterizedTypeReference<List<Account>> ACCOUNT_TYPE_REFERENCE =
            new ParameterizedTypeReference<>() {
            };
    private final RestClient restClient;

    @Override
    public Iterable<Account> findAllAccounts() {
        return this.restClient
                .get()
                .uri("/accounts")
                .retrieve()
                .body(ACCOUNT_TYPE_REFERENCE);
    }

    @Override
    public Account createAccount(String title, BigDecimal balance) {
        try {
            return this.restClient
                    .post()
                    .uri("/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new NewAccountPayload(title, balance))
                    .retrieve()
                    .body(Account.class);
        } catch (HttpClientErrorException.BadRequest e) {
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            //TODO check ProblemDetails
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public Optional<Account> findAccount(int id) {
        try {
            return Optional.ofNullable(this.restClient
                    .get()
                    .uri("/accounts/{accountId}", id)
                    .retrieve()
                    .body(Account.class));

        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateAccount(Integer id, String title, BigDecimal balance) {
        try {
            this.restClient
                    .patch()
                    .uri("/accounts/{accountId}", id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new UpdateAccountPayload(title, balance))
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.BadRequest e) {
            ProblemDetail problemDetail = e.getResponseBodyAs(ProblemDetail.class);
            //TODO check ProblemDetails
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
    }

    @Override
    public void deleteAccount(int id) {
        try {
            this.restClient
                    .delete()
                    .uri("/accounts/{accountId}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound e) {
            throw new NoSuchElementException(e);
        }
    }
}
