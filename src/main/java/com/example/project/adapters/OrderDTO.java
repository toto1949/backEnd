package com.example.project.adapters;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.project.domain.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;
    @NotEmpty
    private String customerName;
    @Email
    private String email;
    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}")
    private String phone;
    @NotEmpty
    private String street;
    @NotEmpty
    private String city;
    @Pattern(regexp = "\\d{5}")
    private String zip;
    @Pattern(regexp = "^(VISA|MasterCard)$")
    private String creditCardType;
    @Pattern(regexp = "\\d{16}")
    private String creditCardNumber;
    private String creditCardExpiry;
    @Pattern(regexp = "\\d{3}")
    private String validationCode;

    private OrderStatus status;
    private LocalDateTime timestamp;
    private List<OrderItemDTO> items;
}
