package ru.barkhatnat.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import ru.barkhatnat.client.AuthInterceptor;
import ru.barkhatnat.controller.payload.GlobalAccessToken;
import ru.barkhatnat.service.RestClientAccountsRestClient;
import ru.barkhatnat.service.RestClientAuth;

@Configuration
@ComponentScan("ru.barkhatnat")
@RequiredArgsConstructor
public class ClientBeans {
    private final GlobalAccessToken accessToken;
    private final AuthInterceptor authInterceptor;

    @Bean
    public RestClientAccountsRestClient restClientAccounts(@Value("${application.services.accounts-list.uri:http://localhost:7128}") String accountListBaseUri) {

        return new RestClientAccountsRestClient(RestClient.builder()
                .baseUrl(accountListBaseUri)
                .requestInterceptor(authInterceptor)
                .build());
    }

    @Bean
    public RestClientAuth restClientAuth(@Value("${application.services.accounts-list.uri:http://localhost:7128}") String accountListBaseUri) {
        return new RestClientAuth(RestClient.builder()
                .baseUrl(accountListBaseUri)
                .build(), accessToken);
    }
}
