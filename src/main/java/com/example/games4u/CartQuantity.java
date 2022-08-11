package com.example.games4u;

public class CartQuantity {
    private int id;
    private int quantity;

    public CartQuantity(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CartQuantity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
