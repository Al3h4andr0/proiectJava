package com.example.proiectjava;

import java.util.List;

public class GeocodeResponse {
    private List<GeocodeResult> results;
    private String status;

    public List<GeocodeResult> getResults() {
        return results;
    }

    public void setResults(List<GeocodeResult> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
