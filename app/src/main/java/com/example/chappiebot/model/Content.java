package com.example.chappiebot.model;

public class Content extends ImageObj {
    public Content() {
    }

    public ImageObj getPreview_image() {
        return preview_image;
    }

    public void setPreview_image(ImageObj preview_image) {
        this.preview_image = preview_image;
    }


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    String caption;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String text;
    int duration;
    ImageObj preview_image;
}
