package com.example.games4u.services;

import com.example.games4u.CartGames;
import com.example.games4u.Game;
import com.example.games4u.SQLiteDataBase;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

@Service
public class MainPageService {

    public List<Game> getGames() {
        Connection conn = SQLiteDataBase.connect();
        List<Game> games = SQLiteDataBase.selectAll(conn);
        List<CartGames> cartGames;
        cartGames = SQLiteDataBase.takeAllCartId();

        // sorting by id to compare proper games
        cartGames.sort(Comparator.comparing(CartGames::getId));
        games.sort(Comparator.comparing(Game::getId));

        for (int i = 0; i < cartGames.size(); i++) {
            if (games.get(i).getId() == cartGames.get(i).getId()) {
                games.get(i).setInCart(true);
            }
        }
        return games;
    }

    public int getQuantity() {
        int quantity = 0;
        List<CartGames> cartGames;
        cartGames = SQLiteDataBase.takeAllCartId();
        for (int i = 0; i < cartGames.size(); i++) {
            int id = cartGames.get(i).getId();
            Game game = SQLiteDataBase.sellectById(id);
            game.setQuantity(cartGames.get(i).getQuantity());
            quantity = quantity + game.getQuantity();
        }
        return quantity;
    }
}
