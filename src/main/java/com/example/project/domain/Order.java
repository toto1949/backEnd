package com.example.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    private String id;
    private List<OrderItem> items;
    private String customerName;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String zip;
    private String creditCardType;
    private String creditCardNumber;
    private String creditCardExpiry;
    private String validationCode;
    private OrderStatus status;
    private LocalDateTime timestamp;

    public Order(String id, List<OrderItem> items, String customerName, String email, String phone, String street,
            String city, String zip, String creditCardType, String creditCardNumber, String creditCardExpiry) {
        this.id = id;
        this.items = items;
        this.customerName = customerName;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.creditCardType = creditCardType;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiry = creditCardExpiry;
        this.status = OrderStatus.PLACED;
        this.timestamp = LocalDateTime.now();
    }
}
