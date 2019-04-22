package com.webshop.users.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address {

    private String country;
    private String city;
    private String postcode;
    private String street;
    private String number;
    private String apartmentNumber;
}
