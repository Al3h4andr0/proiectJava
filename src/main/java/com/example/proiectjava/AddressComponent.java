package com.example.proiectjava;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddressComponent {
    @JsonProperty("long_name")
    private String longName;

    @JsonProperty("short_name")
    private String shortName;

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    private List<String> types;

}
