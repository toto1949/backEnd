package com.example.project.controllers;

import com.example.project.adapters.ProductAdapter;
import com.example.project.adapters.ProductDTO;
import com.example.project.domain.Product;
import com.example.project.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts().stream().map(ProductAdapter::toDTO).toList();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String productId) {
        var product = productService.getProductById(productId);
        return new ResponseEntity<>(ProductAdapter.toDTO(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        if (productService.exists(productDTO.getNumber())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        var product = ProductAdapter.fromDTO(productDTO);

        var savedProduct = productService.addProduct(product);
        return new ResponseEntity<>(ProductAdapter.toDTO(savedProduct), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable String productId,
            @RequestBody @Valid ProductDTO productDTO) {
        if (!productService.exists(productId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!productId.equals(productDTO.getNumber())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        var product = ProductAdapter.fromDTO(productDTO);
        var updated = productService.updateProduct(productId, product);
        return new ResponseEntity<>(ProductAdapter.toDTO(updated), HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable String productId) {
        if (!productService.exists(productId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.removeProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
