package io.github.instagram.client.transport;

public interface Transport {

    <T> ResponseEntry<T> get(String url, Class<T> responseType) throws TransportException;

    <T> ResponseEntry<T> post(String url, Object request, Class<T> responseType);
}
