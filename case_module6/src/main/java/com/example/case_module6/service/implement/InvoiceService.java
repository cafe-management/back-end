package com.example.case_module6.service.implement;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.Invoice;
import com.example.case_module6.repository.CartRepository;
import com.example.case_module6.repository.InvoiceRepository;
import com.example.case_module6.service.IInvoiceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Page<Invoice> getAllInvoice(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    public Invoice createInvoice(Invoice invoice) {
        if (invoice.getDateCreate() == null) {
            invoice.setDateCreate(OffsetDateTime.now(ZoneOffset.ofHours(7)));
        }
        return invoiceRepository.save(invoice);
    }

    @Override
    @Transactional
    public Optional<Cart> assignInvoiceToCart(Long cartId, Long invoiceId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(invoiceId);
        if (optionalCart.isPresent() && optionalInvoice.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setInvoice(optionalInvoice.get());
            cartRepository.save(cart);
            return Optional.of(cart);
        }
        return Optional.empty();
    }
}
