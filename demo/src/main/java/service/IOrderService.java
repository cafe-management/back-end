package service;

import model.Order;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(String orderId);
    Order createOrder(Order order);
    Order updateOrder(String orderId, Order orderDetails);
    void deleteOrder(String orderId);
    List<Order> getOrdersWithHighShippingCost(double minShippingCost);
}
