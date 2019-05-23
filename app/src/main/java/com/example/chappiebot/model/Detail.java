package com.example.chappiebot.model;

import java.util.ArrayList;

public class Detail extends Feed {
    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public Detail() {
    }

    ArrayList<Section> sections;
}
