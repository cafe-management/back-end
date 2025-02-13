package com.example.case_module6.service.implement;

import com.example.case_module6.model.OrderDetail;
import com.example.case_module6.repository.OrderDetailRepository;
import com.example.case_module6.service.IOrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrdersDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public void save(OrderDetail entity) {
        orderDetailRepository.save(entity);
    }

    @Override
    public void update(Long id, OrderDetail entity) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail existingOrderDetail = optionalOrderDetail.get();
            existingOrderDetail.setInvoice(entity.getInvoice());
            existingOrderDetail.setDrink(entity.getDrink());
            existingOrderDetail.setQuantity(entity.getQuantity());
            existingOrderDetail.setTotalPrice(entity.getTotalPrice());
            orderDetailRepository.save(existingOrderDetail);
        } else {
            throw new RuntimeException("OrderDetail với id " + id + " không tồn tại");
        }
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }
}
