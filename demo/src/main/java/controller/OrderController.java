package controller;

import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.IOrderService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders_list")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order_list";
    }

    @GetMapping("/{id}")
    public String getOrderById(@PathVariable(value = "id") String orderId, Model model) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "order_detail";
        }
        model.addAttribute("error", "Order not found!");
        return "error_page"; // Trả về trang lỗi nếu không tìm thấy đơn hàng
    }

    @GetMapping("/create")
    public String showCreateOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "order_form"; // Trả về trang tạo đơn hàng
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return "redirect:/orders"; // Chuyển hướng về danh sách đơn hàng sau khi tạo
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderForm(@PathVariable(value = "id") String orderId, Model model) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "order_form"; // Trả về trang chỉnh sửa đơn hàng
        }
        return "error_page";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable(value = "id") String orderId, @ModelAttribute Order orderDetails) {
        orderService.updateOrder(orderId, orderDetails);
        return "redirect:/orders"; // Chuyển hướng về danh sách đơn hàng sau khi cập nhật
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable(value = "id") String orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/orders"; // Chuyển hướng về danh sách đơn hàng sau khi xóa
    }
}
