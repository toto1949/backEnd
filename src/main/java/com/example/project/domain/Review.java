package com.example.project.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    @Id
    private String id;
    private String username;
    private String productId;
    private String comment;
    private int rating;
    private LocalDateTime timestamp;

    public Review(String id, String username, String productId, String comment, int rating) {
        this.id = id;
        this.username = username;
        this.productId = productId;
        this.comment = comment;
        this.rating = rating;
        this.timestamp = LocalDateTime.now();
    }
}
