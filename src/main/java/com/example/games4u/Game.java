package com.example.games4u;

import java.awt.image.BufferedImage;

public class Game {
    private int id;
    private String name;
    private String type;
    private int price;
    private BufferedImage image;
    private int quantity;
    private boolean isInCart;

    //public Game(int id, String name, String type, int price, Blob image) {
    public Game(int id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.isInCart = isInCart;
    }

    public Game() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInCart() { return isInCart; }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }
}
