package com.example.games4u.services;

import com.example.games4u.CartGames;
import com.example.games4u.Game;
import com.example.games4u.SQLiteDataBase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    public List<Game> getGames(){
        List<Game> gamesIds = new ArrayList<>();
        List<CartGames> cartGames;
        cartGames = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartGames.size(); i++) {
            int id = cartGames.get(i).getId();
            Game game = SQLiteDataBase.sellectById(id);
            game.setQuantity(cartGames.get(i).getQuantity());
            gamesIds.add(game);
        }

        return gamesIds;
    }

    public int getSubtotal() {
        int subtotal = 0;
        List<CartGames> cartGames;
        cartGames = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartGames.size(); i++) {
            int id = cartGames.get(i).getId();
            Game game = SQLiteDataBase.sellectById(id);
            game.setQuantity(cartGames.get(i).getQuantity());
            subtotal = subtotal + (game.getPrice() * game.getQuantity());
        }
        return subtotal;
    }

    public int getQuantity() {
        int quantity = 0;
        List<CartGames> cartGames;
        cartGames = SQLiteDataBase.takeAllCartId();
        for(int i = 0; i < cartGames.size(); i++) {
            int id = cartGames.get(i).getId();
            Game game = SQLiteDataBase.sellectById(id);
            game.setQuantity(cartGames.get(i).getQuantity());
            quantity = quantity + game.getQuantity();
        }
        return quantity;
    }
}
