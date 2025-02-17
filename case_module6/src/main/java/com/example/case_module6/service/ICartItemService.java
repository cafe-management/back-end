package com.example.case_module6.service;

import com.example.case_module6.dto.BestSellingDrinkDTO;
import com.example.case_module6.model.CartItem;

import java.util.List;

public interface ICartItemService extends IService<CartItem,Long> {
    List<BestSellingDrinkDTO> findTopProducts(int limit);
}
