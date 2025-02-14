package com.example.case_module6.service;

import com.example.case_module6.model.Drink;

import java.util.List;

public interface IDrinkService extends IService<Drink,Long> {
    List<Drink>findDrinkByCategory(Long categoryId);
}
