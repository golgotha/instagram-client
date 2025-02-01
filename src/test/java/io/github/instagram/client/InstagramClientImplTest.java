package io.github.instagram.client;

import io.github.instagram.client.transport.ResponseEntry;
import io.github.instagram.client.transport.Transport;
import io.github.instagram.client.transport.TransportException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class InstagramClientImplTest {

    private static final String USER_ID = "1234";
    private static final String ACCESS_TOKEN = "accessToken";

    @Test
    void testCreateInstagramClient() {
        InstagramClient instagramClient = InstagramClientImpl.builder()
                .userId(USER_ID)
                .transport(mock(Transport.class))
                .accessToken(ACCESS_TOKEN)
                .build();
        assertNotNull(instagramClient);
    }

    @Test
    void testCreateInstagramClient_when_userId_is_null() {
        assertThrows(NullPointerException.class, () -> InstagramClientImpl.builder()
                .transport(mock(Transport.class))
                .accessToken(ACCESS_TOKEN)
                .build());
    }

    @Test
    void testCreateInstagramClient_when_transport_is_null() {
        assertThrows(NullPointerException.class, () -> InstagramClientImpl.builder()
                .userId(USER_ID)
                .accessToken(ACCESS_TOKEN)
                .build());
    }

    @Test
    void testCreateInstagramClient_when_access_token_is_null() {
        assertThrows(NullPointerException.class, () -> InstagramClientImpl.builder()
                .userId(USER_ID)
                .transport(mock(Transport.class))
                .build());
    }

    @Test
    void testCreateMedia_image_request() throws TransportException {
        Transport transport = mock(Transport.class);
        InstagramClient instagramClient = InstagramClientImpl.builder()
                .userId(USER_ID)
                .transport(transport)
                .accessToken(ACCESS_TOKEN)
                .build();

        var body = new CreateMediaResponse("00000", "https://rupload.facebook.com/ig-api-upload/v22.0/00000");
        when(transport.get(any(), eq(CreateMediaResponse.class)))
                .thenReturn(new ResponseEntryImpl(200, body));

        var mediaRequest = CreateMediaRequest.imageContainerBuilder()
                .imageUrl("http://localhost/media/123")
                .caption("caption")
                .accessToken("accessToken")
                .build();
        CreateMediaResponse media = instagramClient.createMedia(mediaRequest);

        assertNotNull(media);
        assertEquals(body.getId(), media.getId());
        assertEquals(body.getUrl(), media.getUrl());
        final String url = instagramClient.getApiEndpoint() + "/" + USER_ID + "/media" + mediaRequest.getQueryString();
        verify(transport, times(1)).get(eq(url), eq(CreateMediaResponse.class));
    }

    private record ResponseEntryImpl(int status, CreateMediaResponse body)
            implements ResponseEntry<CreateMediaResponse> {

        @Override
        public int getStatusCode() {
            return status;
        }
    }
}
