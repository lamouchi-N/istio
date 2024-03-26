package com.soa.order.controller;


import basedomains.OrderEvent;
import basedomains.OrderDto;
import com.soa.order.kafka.OrderProducer;
import com.soa.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.*;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderProducer orderProducer;
    private final StreamsBuilderFactoryBean kafkaStreamsFactory;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderProducer orderProducer, StreamsBuilderFactoryBean kafkaStreamsFactory, OrderService orderService) {
        this.orderProducer = orderProducer;
        this.kafkaStreamsFactory = kafkaStreamsFactory;
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody OrderDto orderDto){
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(orderDto);
        orderService.createOrder(orderDto);
        orderProducer.sendMessage(orderEvent);

        return "A new Order is placed successfully !" ;
    }

    @GetMapping("/orders")
    public List<OrderEvent> getAll() {
        List<OrderEvent> orders = new ArrayList<>();
        ReadOnlyKeyValueStore<Long, OrderEvent> store = kafkaStreamsFactory
                .getKafkaStreams()
                .store(StoreQueryParameters.fromNameAndType(
                        "order_topic",
                        QueryableStoreTypes.keyValueStore()));
        KeyValueIterator<Long, OrderEvent> it = store.all();
        it.forEachRemaining(kv -> orders.add(kv.value));
        return orders;
    }
}
