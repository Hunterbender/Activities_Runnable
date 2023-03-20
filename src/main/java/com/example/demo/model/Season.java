package com.example.demo.model;

public class Season {
    private int id;
    private String identifier;

    public Season(int id, String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                '}';
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getId() {
        return id;
    }
}
