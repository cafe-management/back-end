package com.example.caseduan1.dto;

import com.example.caseduan1.model.Order;
import com.example.caseduan1.model.Order.Status;
import com.example.caseduan1.model.Order.PaymentMethod;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private Double totalPrice;
    private Status status;
    private PaymentMethod paymentMethod;
    private LocalDateTime createdAt;
    private Long userId;

    public OrderDTO(Order order) {
        if (order != null) {
            this.id = order.getId();
            this.totalPrice = order.getTotalPrice();
            this.status = order.getStatus();
            this.paymentMethod = order.getPaymentMethod();
            this.createdAt = order.getCreatedAt();
            this.userId = (order.getUser() != null) ? order.getUser().getId() : null; // Kiểm tra null
        }
    }
}
