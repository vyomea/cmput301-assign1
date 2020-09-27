package com.example.vyome_gearbook;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Gear implements Serializable {


    private String date;
    private String maker;
    private String description;
    private double price;
    private String comment;

    public Gear(String date, String maker, String description, double price) {
        this.date = date;
        this.maker = maker;
        this.description = description;
        this.price = price;

    }

    public Gear(String date, String maker, String description, double price, String comment) {
        this.date = date;
        this.maker = maker;
        this.description = description;
        this.price = price;
        this.comment = comment;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
