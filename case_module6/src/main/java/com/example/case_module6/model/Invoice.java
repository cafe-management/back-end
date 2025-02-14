package com.example.case_module6.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_invoice", unique = true, nullable = false)
    private String codeInvoice;

    @Column(name = "date_create", nullable = false)
    private LocalDateTime dateCreate;

    @Column(name = "date_payment")
    private LocalDateTime datePayment;

    @Column(name = "status_order", nullable = false)
    private Boolean statusOrder;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", unique = true)
    private Cart cart;
}