package com.example.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.project.domain.Product;
import com.example.project.domain.Review;
import com.example.project.services.OrderService;
import com.example.project.services.ProductService;
import com.example.project.services.ReviewService;
import com.example.project.domain.Order;
import com.example.project.domain.OrderItem;
import com.example.project.domain.OrderStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BootstrapCommandLineRunner implements CommandLineRunner {
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private OrderService orderService;

    @Override
    public void run(String... args) throws Exception {
        saveProducts();
        saveReviews();
    }

    public void saveProducts() {
        productService.addProduct(new Product("1", "Product 1", 10.0, "Description 1", 10, ""));
        productService.addProduct(new Product("2", "Product 2", 20.0, "Description 2", 20, ""));
        productService.addProduct(new Product("3", "Product 3", 30.0, "Description 3", 30, ""));
        productService.addProduct(new Product("4", "Product 4", 40.0, "Description 4", 40, ""));
        productService.addProduct(new Product("5", "Product 5", 50.0, "Description 5", 50, ""));
        productService.addProduct(new Product("6", "Product 6", 60.0, "Description 6", 60, ""));
        productService.addProduct(new Product("7", "Product 7", 70.0, "Description 7", 70, ""));
        productService.addProduct(new Product("8", "Product 8", 80.0, "Description 8", 80, ""));
        productService.addProduct(new Product("9", "Product 9", 90.0, "Description 9", 90, ""));
        productService.addProduct(new Product("10", "Product 10", 100.0, "Description 10", 100, ""));
    }

    public void saveReviews() {
        reviewService.save(new Review("1", "Bob", "1", "Comment 1", 5));
        reviewService.save(new Review("2", "Alice", "2", "Comment 2", 4));
        reviewService.save(new Review("3", "Charlie", "3", "Comment 3", 3));
        reviewService.save(new Review("4", "David", "4", "Comment 4", 2));
        reviewService.save(new Review("5", "Eve", "5", "Comment 5", 1));
    }
}
