package service;

import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.OrderRepository;
import service.IOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(String orderId, Order orderDetails) {
        return orderRepository.findById(orderId).map(order -> {
            order.setTotalAmount(orderDetails.getTotalAmount());
            order.setShippingCost(orderDetails.getShippingCost());
            order.setOrderType(orderDetails.getOrderType());
            order.setRushFee(orderDetails.getRushFee());
            order.setCustomsFee(orderDetails.getCustomsFee());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getOrdersWithHighShippingCost(double minShippingCost) {
        return orderRepository.findByShippingCostGreaterThanEqual(minShippingCost);
    }
}
