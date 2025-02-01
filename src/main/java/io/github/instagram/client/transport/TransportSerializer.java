package io.github.instagram.client.transport;

public interface TransportSerializer {

    <T> T serialize(String value, Class<T> clazz) throws SerializationException;

}
