package com.example.case_module6.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "BIGINT")
    private Long  id;

    @Column(name = "user_name", unique = true, nullable = false,columnDefinition = "VARCHAR(50)")
    private String userName;

    @Column(name = "pass_word", nullable = false,columnDefinition = "VARCHAR(255)")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}