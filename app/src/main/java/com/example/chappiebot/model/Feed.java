package com.example.chappiebot.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Feed implements Serializable {
    public Feed() {
    }

    String document_id;
    String title;
    String description;
    String content_type;
    String published_date;
    Publisher publisher;
    String origin_url;
    ImageObj avatar;
    ArrayList<ImageObj> images;
    Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getOrigin_url() {
        return origin_url;
    }

    public void setOrigin_url(String origin_url) {
        this.origin_url = origin_url;
    }

    public ArrayList<ImageObj> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageObj> images) {
        this.images = images;
    }


    public String getOriginUrl() {
        return origin_url;
    }

    public void setOriginUrl(String origin_url) {
        this.origin_url = origin_url;
    }

    public ImageObj getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageObj avatar) {
        this.avatar = avatar;
    }


    public String getDocumentId() {
        return document_id;
    }

    public void setDocumentId(String document_id) {
        this.document_id = document_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


}
