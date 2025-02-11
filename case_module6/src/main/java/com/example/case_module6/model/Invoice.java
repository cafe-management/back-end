package com.example.case_module6.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private Date dateCreate;

    @Column(name = "date_payment", nullable = false)
    private Date datePayment;

    @Column(name = "status_order", nullable = false)
    private Boolean statusOrder;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_table", referencedColumnName = "id")
    private TableCoffee tableCoffee;
}