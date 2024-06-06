package ru.barkhatnat.controller.payload;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class GlobalAccessToken {

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;
}
