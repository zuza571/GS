package com.example.games4u.services;

import com.example.games4u.CartGames;
import com.example.games4u.SQLiteDataBase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AboutService {
    public int getQuatntity() {
        int quantity = 0;
        List<CartGames> cartGames;
        cartGames = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartGames.size(); i++) {
            quantity = quantity + cartGames.get(i).getQuantity();
        }
        return quantity;
    }
}
