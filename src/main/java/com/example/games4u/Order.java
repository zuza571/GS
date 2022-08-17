package com.example.games4u;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private int price;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String street;
    private String postcode;
    private String city;
    private String moreInfo;

    // gry i ilosc
}
