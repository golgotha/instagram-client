package io.github.instagram.client;

public class CreateMediaRequest {

    private final String imageUrl;
    private final String videoUrl;
    private final MediaType mediaType;
    private final String accessToken;
    private final Boolean carouselItem;
    private final String caption;

    private CreateMediaRequest(ImageBuilderImpl builder) {
        this.videoUrl = null;
        this.mediaType = null;
        this.imageUrl = Validate.paramNotNull(builder.imageUrl, "Image URL");
        this.accessToken = Validate.paramNotNull(builder.accessToken, "Access Token");
        this.carouselItem = Validate.paramNotNull(builder.carousel, "Carousel");
        this.caption = Validate.paramNotNull(builder.caption, "Caption");
    }

    private CreateMediaRequest(VideoBuilderImpl builder) {
        this.imageUrl = null;
        this.mediaType = null;
        this.videoUrl = Validate.paramNotNull(builder.videoUrl, "Video URL");
        this.accessToken = Validate.paramNotNull(builder.accessToken, "Access Token");
        this.carouselItem = Validate.paramNotNull(builder.carousel, "Carousel");
        this.caption = Validate.paramNotNull(builder.caption, "Caption");
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public boolean isCarouselItem() {
        return carouselItem;
    }

    public String getCaption() {
        return caption;
    }

    public String getQueryString() {
        StringBuilder queryString = new StringBuilder("?");
        if (imageUrl != null) {
            queryString.append("image_url=")
                    .append(imageUrl);
        }

        if (videoUrl != null) {
            queryString.append("video_url=")
                    .append(videoUrl);
        }

        if (carouselItem != null) {
            queryString.append("&is_carousel_item=")
                    .append(carouselItem.booleanValue());
        }

        if (caption != null) {
            queryString.append("&caption=")
                    .append(caption);
        }

        queryString.append("&access_token=").append(accessToken);

        return queryString.toString();
    }


    public static ImageBuilder imageContainerBuilder() {
        return new ImageBuilderImpl();
    }

    public static VideoBuilder videoContainerBuilder() {
        return new VideoBuilderImpl();
    }

    public interface Builder<B extends Builder<B>> {

        /**
         * App user's User access token.
         * @param accessToken
         * @return
         */
        B accessToken(String accessToken);

        CreateMediaRequest build();

    }

    protected abstract static class BuilderImpl<B extends Builder<B>> implements Builder<B> {

        protected String accessToken;

        @Override
        public B accessToken(String accessToken) {
            this.accessToken = accessToken;
            return (B) this;
        }
    }

    public interface ReelsBuilder<B extends Builder<B>> {
        /**
         * Name of the audio of your Reels media.
         * You can only rename once, either while creating a reel or after from the audio page.
         * @param audioName Audio name
         */
        B audioName(String audioName);

        /**
         * The path to an image to use as the cover image for the Reels tab.
         * We will cURL the image using the URL that you specify so the image must be on a public server.
         * If you specify both cover_url and thumb_offset, we use cover_url and ignore thumb_offset. The image must conform to the specifications for a Reels cover photo.
         * @param coverUrl
         * @return
         */
        B coverUrl(String coverUrl);
    }

    public interface ImageBuilder extends StandardContainerBuilder<ImageBuilder> {

        ImageBuilder imageUrl(String imageUrl);

        ImageBuilder carouselItem(boolean carousel);

    }

    public interface VideoBuilder extends StandardContainerBuilder<VideoBuilder> {

        VideoBuilder videoUrl(String imageUrl);

        VideoBuilder carouselItem(boolean carousel);

    }

    public interface StandardContainerBuilder<B extends Builder<B>> extends Builder<B> {

        /**
         * A caption for the image, video, or carousel.
         * Can include hashtags (example: #crazywildebeest) and usernames of Instagram users (example: @natgeo).
         * Mentioned Instagram users receive a notification when the container is published. Maximum 2200 characters, 30 hashtags, and 20 @ tags.
         * @param caption
         * @return
         */
        B caption(String caption);

    }

    private static class ImageBuilderImpl extends BuilderImpl<ImageBuilder> implements ImageBuilder {

        private String imageUrl;
        private boolean carousel;
        private String caption;

        @Override
        public ImageBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        @Override
        public ImageBuilder carouselItem(boolean carousel) {
            this.carousel = carousel;
            return this;
        }

        @Override
        public ImageBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        @Override
        public CreateMediaRequest build() {
            return new CreateMediaRequest(this);
        }
    }

    private static class VideoBuilderImpl extends BuilderImpl<VideoBuilder> implements VideoBuilder {
        private String videoUrl;
        private boolean carousel;
        private String caption;

        @Override
        public VideoBuilder videoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
            return this;
        }

        @Override
        public VideoBuilder carouselItem(boolean carousel) {
            this.carousel = carousel;
            return this;
        }

        @Override
        public VideoBuilder caption(String caption) {
            this.caption = caption;
            return this;
        }

        @Override
        public CreateMediaRequest build() {
            return new CreateMediaRequest(this);
        }
    }
}
