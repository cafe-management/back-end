package service;

import model.Order;
import java.util.List;

public interface IOrderService {
    // Tạo đơn hàng mới
    Order createOrder(Order order);

    // Lấy danh sách tất cả đơn hàng
    List<Order> getAllOrders();

    // Tìm đơn hàng theo ID
    Order getOrderById(int orderId);

    // Cập nhật đơn hàng
    Order updateOrder(int orderId, Order order);

    // Xóa đơn hàng
    boolean deleteOrder(int orderId);
}
