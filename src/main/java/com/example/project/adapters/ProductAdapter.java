package com.example.project.adapters;

import com.example.project.domain.Product;
import com.example.project.adapters.ProductDTO;

public class ProductAdapter {
    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setNumber(product.getNumber());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());
        productDTO.setDescription(product.getDescription());
        productDTO.setNumberInStock(product.getNumberInStock());
        productDTO.setReviews(product.getReviews());
        return productDTO;
    }

    public static Product fromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setNumber(productDTO.getNumber());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        product.setDescription(productDTO.getDescription());
        product.setNumberInStock(productDTO.getNumberInStock());
        product.setReviews(productDTO.getReviews());
        return product;
    }
}
