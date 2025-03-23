package controller;

import model.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.IOrderTypeService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ordertypes")
public class OrderTypeController {

    @Autowired
    private IOrderTypeService orderTypeService;

    @GetMapping
    public String getAllOrderTypes(Model model) {
        List<OrderType> orderTypes = orderTypeService.getAllOrderTypes();
        model.addAttribute("orderTypes", orderTypes);
        return "order_type_list";
    }

    @GetMapping("/{id}")
    public String getOrderTypeById(@PathVariable(value = "id") int id, Model model) {
        Optional<OrderType> orderType = orderTypeService.getOrderTypeById(id);
        if (orderType.isPresent()) {
            model.addAttribute("orderType", orderType.get());
            return "order_type_detail";
        }
        model.addAttribute("error", "Order Type not found!");
        return "error_page";
    }

    @GetMapping("/create")
    public String showCreateOrderTypeForm(Model model) {
        model.addAttribute("orderType", new OrderType());
        return "order_type_form";
    }

    @PostMapping("/create")
    public String createOrderType(@ModelAttribute OrderType orderType) {
        orderTypeService.createOrderType(orderType);
        return "redirect:/ordertypes";
    }

    @GetMapping("/edit/{id}")
    public String showEditOrderTypeForm(@PathVariable(value = "id") int id, Model model) {
        Optional<OrderType> orderType = orderTypeService.getOrderTypeById(id);
        if (orderType.isPresent()) {
            model.addAttribute("orderType", orderType.get());
            return "order_type_form"; // Trả về trang chỉnh sửa loại đơn hàng
        }
        return "error_page";
    }

    @PostMapping("/edit/{id}")
    public String updateOrderType(@PathVariable(value = "id") int id, @ModelAttribute OrderType orderTypeDetails) {
        orderTypeService.updateOrderType(id, orderTypeDetails);
        return "redirect:/ordertypes";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrderType(@PathVariable(value = "id") int id) {
        orderTypeService.deleteOrderType(id);
        return "redirect:/ordertypes";
    }
}
