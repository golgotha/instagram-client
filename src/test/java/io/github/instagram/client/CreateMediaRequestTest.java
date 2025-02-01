package io.github.instagram.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateMediaRequestTest {

    @Test
    void testImageCreateMediaRequest() {
        CreateMediaRequest createMediaRequest = CreateMediaRequest.imageContainerBuilder()
                .imageUrl("http://localhost/img.jpeg")
                .carouselItem(false)
                .caption("caption")
                .accessToken("accessToken")
                .build();

        assertNotNull(createMediaRequest);
        assertEquals("http://localhost/img.jpeg", createMediaRequest.getImageUrl());
        assertFalse(createMediaRequest.isCarouselItem());
        assertEquals("caption", createMediaRequest.getCaption());
        assertEquals("accessToken", createMediaRequest.getAccessToken());
        assertNull(createMediaRequest.getMediaType());
        assertNull(createMediaRequest.getVideoUrl());
    }

    @Test
    void testCarouselImageCreateMediaRequest() {
        CreateMediaRequest createMediaRequest = CreateMediaRequest.imageContainerBuilder()
                .imageUrl("http://localhost/img.jpeg")
                .carouselItem(true)
                .caption("caption")
                .accessToken("accessToken")
                .build();

        assertNotNull(createMediaRequest);
        assertEquals("http://localhost/img.jpeg", createMediaRequest.getImageUrl());
        assertTrue(createMediaRequest.isCarouselItem());
        assertEquals("caption", createMediaRequest.getCaption());
        assertEquals("accessToken", createMediaRequest.getAccessToken());
        assertNull(createMediaRequest.getVideoUrl());
    }

    @Test
    void testVideoCreateMediaRequest() {
        CreateMediaRequest createMediaRequest = CreateMediaRequest.videoContainerBuilder()
                .videoUrl("http://localhost/video.mp4")
                .carouselItem(false)
                .caption("caption")
                .accessToken("accessToken")
                .build();

        assertNotNull(createMediaRequest);
        assertEquals("http://localhost/video.mp4", createMediaRequest.getVideoUrl());
        assertFalse(createMediaRequest.isCarouselItem());
        assertEquals("caption", createMediaRequest.getCaption());
        assertEquals("accessToken", createMediaRequest.getAccessToken());
        assertNull(createMediaRequest.getMediaType());
        assertNull(createMediaRequest.getImageUrl());
    }

    @Test
    void testCarouselVideoCreateMediaRequest() {
        CreateMediaRequest createMediaRequest = CreateMediaRequest.videoContainerBuilder()
                .videoUrl("http://localhost/video.mp4")
                .carouselItem(true)
                .caption("caption")
                .accessToken("accessToken")
                .build();

        assertNotNull(createMediaRequest);
        assertEquals("http://localhost/video.mp4", createMediaRequest.getVideoUrl());
        assertTrue(createMediaRequest.isCarouselItem());
        assertEquals("caption", createMediaRequest.getCaption());
        assertEquals("accessToken", createMediaRequest.getAccessToken());
        assertNull(createMediaRequest.getMediaType());
        assertNull(createMediaRequest.getImageUrl());
    }

    @Test
    void testImageGetQueryString() {
        final String expectedQueryString =
                "?image_url=http://localhost/img.jpeg&is_carousel_item=false&caption=caption&access_token=accessToken";
        CreateMediaRequest createMediaRequest = CreateMediaRequest.imageContainerBuilder()
                .imageUrl("http://localhost/img.jpeg")
                .carouselItem(false)
                .caption("caption")
                .accessToken("accessToken")
                .build();
        String actualQueryString = createMediaRequest.getQueryString();
        assertEquals(expectedQueryString, actualQueryString);
    }

    @Test
    void testVideGetQueryString() {
        final String expectedQueryString =
                "?video_url=http://localhost/video.mp4&is_carousel_item=true&caption=caption&access_token=accessToken";
        CreateMediaRequest createMediaRequest = CreateMediaRequest.videoContainerBuilder()
                .videoUrl("http://localhost/video.mp4")
                .carouselItem(true)
                .caption("caption")
                .accessToken("accessToken")
                .build();
        String actualQueryString = createMediaRequest.getQueryString();
        assertEquals(expectedQueryString, actualQueryString);
    }
}
