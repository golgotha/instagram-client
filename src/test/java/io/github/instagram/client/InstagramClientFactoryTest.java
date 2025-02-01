package io.github.instagram.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstagramClientFactoryTest {

    @Test
    void testCreateInstagramClient() {
        InstagramClientFactory instagramClientFactory = new InstagramClientFactory();
        InstagramClient instagramClient = instagramClientFactory.createClient("123", "accessToken");
        assertNotNull(instagramClient);
    }
}
