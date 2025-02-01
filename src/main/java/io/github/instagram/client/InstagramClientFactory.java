package io.github.instagram.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.instagram.client.transport.*;

public class InstagramClientFactory {

    private final TransportSerializer serializer;

    public InstagramClientFactory() {
        this.serializer = new JacksonTransportSerializer(new ObjectMapper());
    }

    public InstagramClient createClient(String userId, String accessToken) {
        return InstagramClientImpl.builder()
                .transport(new JDKStandardTransport(serializer))
                .userId(userId)
                .accessToken(accessToken)
                .build();
    }

    public AccessToken getCurrentAccessToken(String appId, String appSecretId) throws TransportException {
        AccessTokenClient accessTokenClient = new AccessTokenClient(new JDKStandardTransport(
                new JacksonTransportSerializer(new ObjectMapper())
        ));
        return accessTokenClient.fetchAccessToken(appId, appSecretId);
    }

    private static class AccessTokenClient {

        private static final String AUTH_API_ENDPOINT_TEMPLATE =
                "https://graph.facebook.com/oauth/access_token?client_id={app_id}&client_secret={app_secret_id}&grant_type=client_credentials";

        private final Transport transport;

        private AccessTokenClient(Transport transport) {
            this.transport = transport;
        }

        public AccessToken fetchAccessToken(String appId, String appSecretId) throws TransportException {
            String url = AUTH_API_ENDPOINT_TEMPLATE.replace("{app_id}", appId)
                    .replace("{app_secret_id}", appSecretId);

            ResponseEntry<AccessToken> response = transport.get(url, AccessToken.class);
            return response.body();
        }
    }
}
