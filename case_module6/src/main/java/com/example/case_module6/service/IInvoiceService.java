package com.example.case_module6.service;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IInvoiceService {
    Page<Invoice> getAllInvoice(Pageable pageable);
    Invoice createInvoice(Invoice invoice);
    Optional<Cart> assignInvoiceToCart(Long cartId, Long invoiceId);
}
