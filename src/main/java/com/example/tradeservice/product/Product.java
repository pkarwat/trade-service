package com.example.tradeservice.product;

import lombok.Data;

@Data
class Product {
    private int id; // here we can use ProductId instead of int
    private String name;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
