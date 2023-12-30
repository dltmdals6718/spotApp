package com.example.spotapp.dto.location;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("id")
    private Long id;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    @SerializedName("title")
    private String title;

    @SerializedName("address")
    private String address;

    @SerializedName("description")
    private String description;


    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
