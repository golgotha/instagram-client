package io.github.instagram.client;

import io.github.instagram.client.transport.ResponseEntry;
import io.github.instagram.client.transport.Transport;
import io.github.instagram.client.transport.TransportException;

public class InstagramClientImpl implements InstagramClient {

    private final Transport transport;
    private final String userId;

    private InstagramClientImpl(Transport transport, String userId) {
        this.transport = transport;
        this.userId = userId;
    }


    @Override
    public CreateMediaResponse createMedia(CreateMediaRequest request) throws TransportException {
        String queryString = request.getQueryString();
        String url = buildUrl("/{ig-user-id}/media", queryString);
        ResponseEntry<CreateMediaResponse> response = transport.get(url, CreateMediaResponse.class);
        return response.body();
    }

    public static Builder builder() {
        return new Builder();
    }

    private String buildUrl(String pathTemplate, String queryString) {
        String apiEndpoint = getApiEndpoint();
        final String path = pathTemplate.replace("{ig-user-id}", userId);
        return apiEndpoint + path + queryString;
    }

    public static class Builder {
        private Transport transport;
        private String userId;
        private String accessToken;

        public Builder transport(Transport transport) {
            this.transport = transport;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public InstagramClient build() {
            Validate.paramNotNull(userId, "user ID");
            Validate.paramNotNull(transport, "Transport");
            Validate.paramNotNull(accessToken, "Access Token");
            return new InstagramClientImpl(transport, userId);
        }
    }
}
