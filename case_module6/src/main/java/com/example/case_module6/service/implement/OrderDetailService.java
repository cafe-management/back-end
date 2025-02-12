package com.example.case_module6.service.implement;

import com.example.case_module6.dto.BestSellingDrinkDTO;
import com.example.case_module6.model.OrderDetail;
import com.example.case_module6.repository.OrderDetailRepository;
import com.example.case_module6.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public void save(OrderDetail entity) {
        orderDetailRepository.save(entity);
    }

    @Override
    public void update(Long id, OrderDetail entity) {
        orderDetailRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    public List<BestSellingDrinkDTO> getTop10BestSellingDrinks() {
        return orderDetailRepository.findTop10BestSellingDrinks(PageRequest.of(0, 5));
    }

}
