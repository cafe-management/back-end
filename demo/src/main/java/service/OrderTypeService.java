package service.impl;

import model.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderTypeRepository;
import service.IOrderTypeService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderTypeService implements IOrderTypeService {

    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @Override
    public List<OrderType> getAllOrderTypes() {
        return orderTypeRepository.findAll();
    }

    @Override
    public Optional<OrderType> getOrderTypeById(int typeId) {
        return orderTypeRepository.findById(typeId);
    }

    @Override
    public OrderType createOrderType(OrderType orderType) {
        return orderTypeRepository.save(orderType);
    }

    @Override
    public OrderType updateOrderType(int typeId, OrderType orderTypeDetails) {
        return orderTypeRepository.findById(typeId).map(orderType -> {
            orderType.setTypeName(orderTypeDetails.getTypeName());
            return orderTypeRepository.save(orderType);
        }).orElseThrow(() -> new RuntimeException("OrderType not found"));
    }

    @Override
    public void deleteOrderType(int typeId) {
        orderTypeRepository.deleteById(typeId);
    }
}
