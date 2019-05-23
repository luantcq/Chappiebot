package com.example.chappiebot.model;

import java.io.Serializable;

public class ImageObj implements Serializable {
    public ImageObj() {
    }

    String href;
    String main_color;
    int width;
    int height;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getMain_color() {
        return main_color;
    }

    public void setMain_color(String main_color) {
        this.main_color = main_color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
