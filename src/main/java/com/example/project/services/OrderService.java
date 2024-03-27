package com.example.project.services;

import com.example.project.adapters.OrderAdapter;
import com.example.project.adapters.OrderDTO;
import com.example.project.domain.Order;
import com.example.project.domain.OrderItem;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.project.domain.OrderStatus;
import com.example.project.repositories.OrderRepository;
import com.example.project.repositories.ProductRepository;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = OrderAdapter.fromDTO(orderDTO);
        if (!isValid(order))
            return null;
        for (OrderItem item : order.getItems()) {
            var product = productRepository.findById(item.getProductId()).get();
            product.setNumberInStock(product.getNumberInStock() - item.getQuantity());
            productRepository.save(product);
        }
        order.setTimestamp(LocalDateTime.now());
        return OrderAdapter.toDTO(orderRepository.save(order));
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId).get();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void removeOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).get();
        order.setStatus(status);
        orderRepository.save(order);
    }

    public Order update(Order order) {
        order.setTimestamp(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public boolean isValid(Order order) {
        for (OrderItem item : order.getItems()) {
            if (!productRepository.existsById(item.getProductId()))
                return false;
            var product = productRepository.findById(item.getProductId()).get();
            if (product.getNumberInStock() < item.getQuantity())
                return false;
        }
        return true;
    }
}
