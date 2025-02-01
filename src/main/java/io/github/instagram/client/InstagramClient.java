package io.github.instagram.client;

import io.github.instagram.client.transport.TransportException;

public interface InstagramClient {

    String FACEBOOK_API_ENDPOINT = "https://graph.instagram.com/%s";

    CreateMediaResponse createMedia(CreateMediaRequest request) throws TransportException;

    default String getApiEndpoint(String version) {
        return String.format(FACEBOOK_API_ENDPOINT, version);
    }

    default String getApiEndpoint() {
        return getApiEndpoint("v22.0");
    }
}
