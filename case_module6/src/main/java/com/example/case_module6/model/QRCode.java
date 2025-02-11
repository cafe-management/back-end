 package com.example.case_module6.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qr_codes")
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qr_img")
    private String qrImg;

    @OneToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private TableCoffee tableCoffee;
}

