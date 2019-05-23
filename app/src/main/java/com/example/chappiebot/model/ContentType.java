package com.example.chappiebot.model;

public enum ContentType {
    OVERVIEW("overview"),
    STORY("story"),
    ARTICLE("article"),
    GALLERY("gallery");

    private String url;

    ContentType(String envUrl) {
        this.url = envUrl;
    }

    public String getType() {
        return url;
    }
}
