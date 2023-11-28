package com.example.shelfapp.Models;

public enum Genre {
    NOVEL("Novel"),
    EDUCATIONAL("Educational"),
    CHILDREN("Children"),
    DETECTIVE("Detective"),
    HISTORY("History"),
    SCI_FI("Sci-Fi"),
    ARTS("Arts"),
    FANTASY("Fantasy"),
    BIOGRAPHY_AUTOBIOGRAPHY("Biography-Autobiography"),
    ADVENTURE("Adventure");

    private final String description;
    private Genre(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

}
