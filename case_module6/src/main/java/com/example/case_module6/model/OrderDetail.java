package com.example.case_module6.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "id")
    private Drink drink;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}