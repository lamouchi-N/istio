package com.soa.order.service;

import basedomains.OrderDto;
import com.soa.order.model.Order;
import com.soa.order.model.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    final private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(OrderDto orderDto){
        Order order = Order
                .builder()
                .orderId(UUID.randomUUID().toString())
                .customerId(orderDto.getCustomerId())
                .productId(orderDto.getProductId())
                .quantity(orderDto.getQuantity())
                .build();
        orderRepository.save(order);
        return orderDto;

    }
}
