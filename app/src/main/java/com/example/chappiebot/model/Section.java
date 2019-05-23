package com.example.chappiebot.model;

public class Section {

    public Section() {
    }

    public int getSection_type() {
        return section_type;
    }

    public void setSection_type(int section_type) {
        this.section_type = section_type;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
    int section_type;
    Content content;
}
