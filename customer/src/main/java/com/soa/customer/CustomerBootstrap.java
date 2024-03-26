package com.soa.customer;

import com.soa.customer.model.Customer;
import com.soa.customer.model.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CustomerBootstrap implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerBootstrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer c1 = Customer.builder().name("Ali Ben Saleh")
                .build();

        Customer c2 = Customer.builder().name("Fatma")
                .build();

        Customer c3 = Customer.builder().name("Mohamed")
                .build();




        customerRepository.saveAll(List.of(c1, c2, c3));
    }
}
