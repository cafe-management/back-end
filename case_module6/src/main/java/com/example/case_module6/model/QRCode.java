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

    @Lob
    @Column(name = "qr_img_url")
    private byte[] qrImgUrl;

    @ManyToOne
    @JoinColumn(name = "id_table", referencedColumnName = "id")
    private TableCoffee tableCoffee;
}

