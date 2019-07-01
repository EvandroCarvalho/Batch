package com.example.createFileBatch.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Address {

    private String street;
    private String city;
    private String number;


    @Override
    public String toString() {
        return street  +
                "," + city +
                "," + number;
    }
}

