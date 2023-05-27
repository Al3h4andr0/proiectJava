package com.example.proiectjava;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressCorrectionService {
    private static final String GEOCODE_API = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";

    private final RestTemplate restTemplate = new RestTemplate();



    public PostalAddress correctAddress(PostalAddress address){
        String formattedAddress = String.format("%s,%s,%s", address.getCity(), address.getState(), address.getCountry());
        String requestUrl = String.format(GEOCODE_API, formattedAddress, "AIzaSyANcPBYkBkF9SPDou4WGXpZn1NuvvrPKKY");
        GeocodeResponse response = restTemplate.getForObject(requestUrl, GeocodeResponse.class);


        if (response != null && "OK".equals(response.getStatus()) && !response.getResults().isEmpty()) {
            GeocodeResult result = response.getResults().get(0);
            for (AddressComponent component : result.getAddressComponents()) {
                if (component.getTypes().contains("locality")) {
                    address.setCity(component.getLongName());
                } else if (component.getTypes().contains("administrative_area_level_1")) {
                    address.setState(component.getLongName());
                } else if (component.getTypes().contains("country")) {
                    address.setCountry(component.getLongName());
                }
            }
        }

        return address;
    }

}