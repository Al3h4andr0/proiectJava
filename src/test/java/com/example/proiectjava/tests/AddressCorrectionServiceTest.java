package com.example.proiectjava.tests;

import com.example.proiectjava.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AddressCorrectionServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RestTemplate mockRestTemplate;

    @InjectMocks
    private AddressCorrectionService addressCorrectionService = new AddressCorrectionService();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCorrectAddress() {
        // Arrange
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={key}";
        GeocodeResponse mockResponse = createMockResponse();

        Mockito.when(mockRestTemplate.getForObject(
                        Mockito.eq(url),
                        Mockito.eq(GeocodeResponse.class),
                        Mockito.anyMap()))
                .thenReturn(mockResponse);

        // Act
        PostalAddress currentaddress = new PostalAddress("RO", "New York", "Iasi");
        PostalAddress correctedAddress = addressCorrectionService.correctAddress(currentaddress);

        // Assert
        assertEquals("Romania", correctedAddress.getCountry());
        assertEquals("Iași County", correctedAddress.getState());
        assertEquals("Iași", correctedAddress.getCity());
    }


    private GeocodeResponse createMockResponse() {
        GeocodeResponse response = new GeocodeResponse();

        GeocodeResult result = new GeocodeResult();
        List<AddressComponent> components = new ArrayList<>();

        AddressComponent countryComponent = new AddressComponent();
        countryComponent.setLongName("Romania");
        countryComponent.setShortName("RO");
        countryComponent.setTypes(Collections.singletonList("country"));
        components.add(countryComponent);

        AddressComponent stateComponent = new AddressComponent();
        stateComponent.setLongName("Iași County");
        stateComponent.setShortName("Iași County");
        stateComponent.setTypes(Collections.singletonList("administrative_area_level_1"));
        components.add(stateComponent);

        AddressComponent cityComponent = new AddressComponent();
        cityComponent.setLongName("Iași");
        cityComponent.setShortName("Iași");
        cityComponent.setTypes(Collections.singletonList("locality"));
        components.add(cityComponent);

        result.setAddressComponents(components);
        response.setResults(Collections.singletonList(result));
        response.setStatus("OK");

        return response;
    }

}
