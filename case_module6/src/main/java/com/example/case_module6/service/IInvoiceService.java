package com.example.case_module6.service;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.Invoice;

import java.util.Optional;

public interface IInvoiceService {
    Invoice createInvoice(Invoice invoice);
    Optional<Cart> assignInvoiceToCart(Long cartId, Long invoiceId);
}
