package com.example.vyome_gearbook;

import android.os.Parcel;
import android.os.Parcelable;

public class Gear implements Parcelable {


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

    public Gear(Parcel pc) {
        date = pc.readString();
        maker = pc.readString();
        description = pc.readString();
        price = pc.readDouble();
        comment = pc.readString();
    }

    public static final Parcelable.Creator<Gear> CREATOR = new Parcelable.Creator<Gear>() {
        public Gear createFromParcel(Parcel pc) {
            return new Gear(pc);
        }

        @Override
        public Gear[] newArray(int size) {
            return new Gear[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.maker);
        dest.writeString(this.description);
        dest.writeDouble(this.price);
        dest.writeString(this.comment);

    }
}
