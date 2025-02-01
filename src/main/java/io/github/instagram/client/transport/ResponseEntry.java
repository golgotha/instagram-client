package io.github.instagram.client.transport;

public interface ResponseEntry<O> {

    int getStatusCode();

    O body();

}
