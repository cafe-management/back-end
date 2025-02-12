package com.example.case_module6.controller;

import com.example.case_module6.model.OrderDetail;
import com.example.case_module6.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order_details")
public class OrderDetailRestController {
    @Autowired
    private IOrderDetailService orderDetailService;
    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        return new ResponseEntity<>(orderDetailService.getAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.save(orderDetail);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") long id, @RequestBody OrderDetail orderDetail) {
        OrderDetail exitingDetail = orderDetailService.findById(id);
        if (exitingDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailService.update(id, orderDetail);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<OrderDetail> deleteOrderDetail(@PathVariable("id") long id) {
        OrderDetail exitsDetail = orderDetailService.findById(id);
        if (exitsDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailService.delete(id);
        return new ResponseEntity<>(exitsDetail, HttpStatus.OK);
    }
}
