package com.echouaibi.moviesappechouaibi;

import com.google.gson.annotations.SerializedName;

public class Trailer {
    @SerializedName("id")
    private int id;

    @SerializedName("key")
    private String key;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
