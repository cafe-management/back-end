package service.impl;

import model.Order;
import service.IOrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void updateOrder(int orderId, Order newOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId() == orderId) {
                orders.set(i, newOrder);
                return;
            }
        }
    }

    @Override
    public void deleteOrder(int orderId) {
        orders.removeIf(order -> order.getOrderId() == orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> getOrdersWithHighShippingCost(double minCost) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getShippingCost() >= minCost) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public double getAverageTotalAmountForInternationalOrders() {
        double sum = 0;
        int count = 0;
        for (Order order : orders) {
            if (order instanceof model.InternationalOrder) {
                sum += order.getTotalAmount();
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }
}
