 package com.example.case_module6.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 @Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feed_backs")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_feed_back", unique = true, nullable = false)
    private String codeFeedback;

    @Column(name = "date_feed_back", nullable = false)
    private LocalDateTime dateFeedback;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @Column(nullable = false)
    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "feedback_id")
    private List<Image> images = new ArrayList<>();
}

