package io.github.instagram.client;

public class CreateMediaResponse {

    /**
     * IG container ID
     */
    private final String id;
    private final String url;

    public CreateMediaResponse(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
