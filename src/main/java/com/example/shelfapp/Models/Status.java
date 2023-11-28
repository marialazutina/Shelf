package com.example.shelfapp.Models;

public enum Status {
    AVAILABLE("Available"),
    RESERVED("Reserved"),
    SOLD("Sold");

    private final String statusInfo;
    private Status(String statusInfo){
        this.statusInfo = statusInfo;
    }


}
