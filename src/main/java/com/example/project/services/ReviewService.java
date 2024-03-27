package com.example.project.services;

import com.example.project.domain.Order;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.project.domain.OrderStatus;
import com.example.project.domain.Review;
import com.example.project.repositories.OrderRepository;
import com.example.project.repositories.ReviewRepository;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository repo;

    public Review save(Review review) {
        review.setTimestamp(LocalDateTime.now());
        return repo.save(review);
    }

    public void delete(Review review) {
        repo.delete(review);
    }

    public Review update(Review review) {
        review.setTimestamp(LocalDateTime.now());
        return repo.save(review);
    }

    public List<Review> getReviewsForProduct(String productId) {
        return repo.findByProductId(productId);
    }

}
