package com.example.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    private String number;
    private String name;
    private double price;
    private String imageUrl;
    private String description;
    private int numberInStock;
    private List<Review> reviews;

    public Product(String number, String name, double price, String description, int numberInStock, String imageUrl) {
        this.number = number;
        this.name = name;
        this.price = price;
        this.description = description;
        this.numberInStock = numberInStock;
        this.imageUrl = imageUrl;
    }
}
