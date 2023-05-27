package com.example.proiectjava;

import com.example.proiectjava.AddressCorrectionService;
import com.example.proiectjava.PostalAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class AddressCorrectionController {

        private final AddressCorrectionService addressCorrectionService;

        @Autowired
        public AddressCorrectionController(AddressCorrectionService addressCorrectionService) {
            this.addressCorrectionService = addressCorrectionService;
        }
        @CrossOrigin(origins = "*")
        @GetMapping("/correctAddress")
        public PostalAddress correctAddress(@RequestParam String country, @RequestParam String state, @RequestParam String city) {
            PostalAddress address = new PostalAddress(country, state, city);
            return addressCorrectionService.correctAddress(address);
        }
    }

