package com.example.case_module6.service;
import com.example.case_module6.dto.BestSellingDrinkDTO;
import com.example.case_module6.model.OrderDetail;

import java.util.List;

public interface IOrderDetailService extends IService<OrderDetail, Long>{

    List<BestSellingDrinkDTO> getTop10BestSellingDrinks();
}
