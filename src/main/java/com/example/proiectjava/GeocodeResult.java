package com.example.proiectjava;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeocodeResult {
    @JsonProperty("address_components")
    private List<AddressComponent> addressComponents;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }
}
