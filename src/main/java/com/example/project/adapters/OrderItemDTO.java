package com.example.project.adapters;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    @NotEmpty
    private String productId;
    @Min(1)
    private int quantity;
    private String name;
    private double price;
}
