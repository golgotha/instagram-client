package io.github.instagram.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {

    private final String accessToken;
    private final String tokenType;

    @JsonCreator
    private AccessToken(@JsonProperty("access_token") String accessToken,
                        @JsonProperty("token_type") String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}
