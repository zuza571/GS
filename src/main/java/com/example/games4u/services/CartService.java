package com.example.games4u.services;

import org.springframework.stereotype.Service;

@Service
public class CartService {

    public static void increaseItem(int itemIndex) {
        System.out.println("+");
        // todo: lista produktow w koszyku - cartItems
        /*
         int amount = cartItems.get(itemIndex).getAmount();
        cartItems.get(itemIndex).setAmount(++amount);
         */
    }

    public static void decreaseItem(int itemIndex) {
        System.out.println("-");
        // todo: lista produktow w koszyku - cartItems
        /*
        int amount = cartItems.get(itemIndex).getAmount();
        if (amount > 1) {
            cartItems.get(itemIndex).setAmount(--amount);
        }
         */
    }
}
