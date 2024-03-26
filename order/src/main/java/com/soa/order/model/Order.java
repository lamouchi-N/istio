package com.soa.order.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Order_Details")
    public class Order {
        @Id
        private  String orderId;
        private Long customerId;
        private Long productId;
        private int quantity;
    }


