package com.soa.stock.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StockBootstrap implements CommandLineRunner {
    private final ProductRepository repository;

    @Autowired
    public StockBootstrap(ProductRepository repository)  {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product p1=Product.builder().name("pc-laptop")
                .availableItems(5000)
                .build();

        Product p2=Product.builder().name("Samsung A70")
                .availableItems(3500)
                .build();

        Product p3=Product.builder().name("iphone")
                .availableItems(100)
                .build();

        Product p4=Product.builder().name("headphone")
                .availableItems(120)
                .build();

        repository.saveAll(List.of(p1,p2,p3,p4));
    }
}
