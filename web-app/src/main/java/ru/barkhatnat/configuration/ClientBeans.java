package ru.barkhatnat.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import ru.barkhatnat.client.RestClientAccountsRestClient;

@Configuration
@ComponentScan("ru.barkhatnat")
public class ClientBeans {
    @Bean
    public RestClientAccountsRestClient restClientAccounts(@Value("${application.services.accounts-list.uri:http://localhost:7128}") String accountListBaseUri){
        return new RestClientAccountsRestClient(RestClient.builder()
                .baseUrl(accountListBaseUri)
                .build());
    }
}
