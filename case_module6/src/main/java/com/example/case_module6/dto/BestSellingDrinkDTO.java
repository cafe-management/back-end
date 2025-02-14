package com.example.case_module6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BestSellingDrinkDTO {
        private Long id;
        private String drinkName;
        private BigDecimal price;
        private String imgDrinks;
        private Long totalQuantity;
}
