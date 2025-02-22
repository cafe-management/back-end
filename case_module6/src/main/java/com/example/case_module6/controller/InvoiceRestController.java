package com.example.case_module6.controller;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.Category;
import com.example.case_module6.model.Invoice;
import com.example.case_module6.service.IInvoiceService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceRestController {
    @Autowired
    private IInvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<Page<Invoice>> getAllInvoices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Invoice> invoices = invoiceService.getAllInvoice(pageable);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceService.createInvoice(invoice);
        return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
    }

    // Endpoint gán hóa đơn cho giỏ hàng
    // Ví dụ: PUT /api/invoices/{invoiceId}/cart/{cartId}
    @PutMapping("/{invoiceId}/cart/{cartId}")
    public ResponseEntity<?> assignInvoiceToCart(@PathVariable Long invoiceId, @PathVariable Long cartId) {
        Optional<Cart> updatedCart = invoiceService.assignInvoiceToCart(cartId, invoiceId);
        if (updatedCart.isPresent()) {
            return ResponseEntity.ok(updatedCart.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart hoặc Invoice không tồn tại");
        }
    }
}
