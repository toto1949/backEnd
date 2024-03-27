package com.example.project.adapters;

import com.example.project.domain.Order;
import com.example.project.domain.OrderItem;

public class OrderAdapter {
    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        var itemDTOs = order.getItems().stream().map(OrderAdapter::toItemDTO).toList();
        orderDTO.setItems(itemDTOs);
        orderDTO.setStatus(order.getStatus());
        orderDTO.setEmail(order.getEmail());
        orderDTO.setPhone(order.getPhone());
        orderDTO.setStreet(order.getStreet());
        orderDTO.setCity(order.getCity());
        orderDTO.setZip(order.getZip());
        orderDTO.setCreditCardType(order.getCreditCardType());
        orderDTO.setCreditCardNumber(order.getCreditCardNumber());
        orderDTO.setCreditCardExpiry(order.getCreditCardExpiry());
        orderDTO.setValidationCode(order.getValidationCode());
        orderDTO.setTimestamp(order.getTimestamp());
        return orderDTO;
    }

    public static Order fromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        var items = orderDTO.getItems().stream().map(OrderAdapter::fromItemDTO).toList();
        order.setItems(items);
        order.setStatus(orderDTO.getStatus());
        order.setEmail(orderDTO.getEmail());
        order.setPhone(orderDTO.getPhone());
        order.setStreet(orderDTO.getStreet());
        order.setCity(orderDTO.getCity());
        order.setZip(orderDTO.getZip());
        order.setCreditCardType(orderDTO.getCreditCardType());
        order.setCreditCardNumber(orderDTO.getCreditCardNumber());
        order.setCreditCardExpiry(orderDTO.getCreditCardExpiry());
        order.setValidationCode(orderDTO.getValidationCode());
        order.setTimestamp(orderDTO.getTimestamp());
        return order;
    }

    public static OrderItemDTO toItemDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductId(orderItem.getProductId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setName(orderItem.getName());
        orderItemDTO.setPrice(orderItem.getPrice());
        return orderItemDTO;
    }

    public static OrderItem fromItemDTO(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDTO.getProductId());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setName(orderItemDTO.getName());
        orderItem.setPrice(orderItemDTO.getPrice());
        return orderItem;
    }
}
