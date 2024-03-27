package com.example.project.adapters;

import com.example.project.domain.Review;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @NotEmpty
    private String number;
    @NotEmpty
    private String name;
    @Min(0)
    private double price;
    private String imageUrl;
    @Size(min = 10)
    private String description;
    @Min(0)
    private int numberInStock;
    private List<Review> reviews;
}
