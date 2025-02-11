package com.example.case_module6.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "table_coffees")
public class TableCoffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_table", nullable = false)
    private Integer numberTable;

    @Column(name = "status_table", nullable = false)
    private Integer statusTable;
}

