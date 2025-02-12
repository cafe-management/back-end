package com.example.case_module6.controller;

import com.example.case_module6.model.Invoice;
import com.example.case_module6.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/invoices")
public class InvoiceRestController {
    @Autowired
    private IInvoiceService invoiceService;
    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return new ResponseEntity<>(invoiceService.getAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        invoiceService.save(invoice);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
        Invoice exitsInvoice = invoiceService.findById(id);
        if (exitsInvoice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        invoiceService.update(id, invoice);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
        Invoice exitsInvoice = invoiceService.findById(id);
        if (exitsInvoice == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        invoiceService.delete(id);
        return new ResponseEntity<>(exitsInvoice, HttpStatus.OK);
    }
}
