package com.example.case_module6.DTO;

import com.example.case_module6.model.Drink;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BestSellingDrinkDTO {
    private Drink drink;
    private Long totalQuantity;
}
