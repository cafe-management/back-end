package com.example.case_module6.controller;

import com.example.case_module6.model.OrderDetail;
import com.example.case_module6.service.IOrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
@CrossOrigin(origins = "**")
public class OrderDetailRestController {
    @Autowired
    private IOrdersDetailService ordersDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = ordersDetailService.getAll();
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable("id") Long id) {
        OrderDetail orderDetail = ordersDetailService.findById(id);
        if (orderDetail != null) {
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        ordersDetailService.save(orderDetail);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") Long id,
                                                         @RequestBody OrderDetail orderDetail) {
        try {
            ordersDetailService.update(id, orderDetail);
            return new ResponseEntity<>(orderDetail, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("id") Long id) {
        ordersDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
