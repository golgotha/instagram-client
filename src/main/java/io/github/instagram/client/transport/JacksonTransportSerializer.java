package io.github.instagram.client.transport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTransportSerializer implements TransportSerializer {

    private final ObjectMapper mapper;

    public JacksonTransportSerializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T serialize(String value, Class<T> clazz) throws SerializationException {
        try {
            return mapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Can not serialize value:" + value, e);
        }
    }
}
