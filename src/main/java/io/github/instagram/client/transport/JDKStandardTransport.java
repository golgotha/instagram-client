package io.github.instagram.client.transport;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JDKStandardTransport implements Transport {

    private final TransportSerializer serializer;
    private final HttpClient client;

    public JDKStandardTransport(TransportSerializer serializer) {
        this.serializer = serializer;
        this.client = HttpClient.newHttpClient();
    }

    @Override
    public <T> ResponseEntry<T> get(String url, Class<T> responseType) throws TransportException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            T content = serializer.serialize(body, responseType);
            return new ResponseEntryImpl<>(response.statusCode(), content);
        } catch (IOException | InterruptedException e) {
            throw new TransportException("Unable to handle a request", e);
        }
    }

    @Override
    public <T> ResponseEntry<T> post(String url, Object request, Class<T> responseType) {
        return null;
    }

    private static class ResponseEntryImpl<T> implements ResponseEntry<T> {
        private final int statusCode;
        private final T body;

        public ResponseEntryImpl(int statusCode, T body) {
            this.statusCode = statusCode;
            this.body = body;
        }

        @Override
        public int getStatusCode() {
            return statusCode;
        }

        @Override
        public T body() {
            return body;
        }
    }
}
