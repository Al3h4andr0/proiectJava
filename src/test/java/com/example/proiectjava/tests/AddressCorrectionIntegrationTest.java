package com.example.proiectjava.tests;

import com.example.proiectjava.PostalAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressCorrectionIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCorrectAddress() {
        PostalAddress response = this.restTemplate.getForObject("http://localhost:" + port + "/correctAddress?country=RO&state=New%20York&city=Iasi", PostalAddress.class);

        assertThat(response.getCountry()).isEqualTo("Romania");
        assertThat(response.getState()).isEqualTo("Iași County");
        assertThat(response.getCity()).isEqualTo("Iași");
    }
}
