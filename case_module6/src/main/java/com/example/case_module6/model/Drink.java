    package com.example.case_module6.model;

    import jakarta.persistence.*;
    import lombok.*;

    import java.math.BigDecimal;

    @Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "drinks")
    public class Drink {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "ma_so_mon", unique = true, nullable = false)
        private String maSoMon;

        @Column(name = "name_drinks", nullable = false)
        private String nameDrinks;

        @ManyToOne
        @JoinColumn(name = "id_category", referencedColumnName = "id")
        private Category category;

        @Column(nullable = false)
        private BigDecimal price;

        @Column(name = "img_drinks")
        private String imgDrinks;
    }