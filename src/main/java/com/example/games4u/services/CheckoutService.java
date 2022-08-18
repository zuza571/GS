package com.example.games4u.services;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class CheckoutService {
    public void orderInfo(String order) throws FileNotFoundException {
        // remove more than one space
        order = order.trim().replaceAll(" +", " ");
        order += "\n";
        // save to file
        try {
            Files.write(Paths.get("orders.txt"), order.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }

    }
}
