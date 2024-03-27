package com.example.project.controllers;

import com.example.project.domain.Review;
import com.example.project.services.ProductService;
import com.example.project.services.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.project.domain.Product;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable String productId) {
        var reviews = reviewService.getReviewsForProduct(productId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        if (!productService.exists(review.getProductId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var savedReview = reviewService.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }
}
