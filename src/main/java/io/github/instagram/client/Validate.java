package io.github.instagram.client;

public class Validate {

    public static <T> T paramNotNull(T object, String paramName) {
        if (object == null) {
            throw new NullPointerException(String.format("%s must not be null.", paramName));
        } else {
            return object;
        }
    }
}
