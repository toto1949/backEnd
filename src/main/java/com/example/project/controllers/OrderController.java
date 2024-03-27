package com.example.project.controllers;

import com.example.project.adapters.OrderAdapter;
import com.example.project.adapters.OrderDTO;
import com.example.project.domain.Order;
import com.example.project.services.OrderService;
import com.example.project.domain.OrderStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders().stream().map(OrderAdapter::toDTO).toList();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable String orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(OrderAdapter.toDTO(order), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO placedOrder = orderService.save(orderDTO);
        System.out.println(placedOrder);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable String orderId, @RequestBody OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
