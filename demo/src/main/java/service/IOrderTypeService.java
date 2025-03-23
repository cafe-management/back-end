package service;

import model.OrderType;
import java.util.List;
import java.util.Optional;

public interface IOrderTypeService {
    List<OrderType> getAllOrderTypes();
    Optional<OrderType> getOrderTypeById(int typeId);
    OrderType createOrderType(OrderType orderType);
    OrderType updateOrderType(int typeId, OrderType orderTypeDetails);
    void deleteOrderType(int typeId);
}
