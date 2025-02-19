package com.example.case_module6.service.implement;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.Invoice;
import com.example.case_module6.repository.CartRepository;
import com.example.case_module6.repository.InvoiceRepository;
import com.example.case_module6.service.IInvoiceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public Invoice createInvoice(Invoice invoice) {
        // Thiết lập thời gian tạo hóa đơn nếu chưa có
        if (invoice.getDateCreate() == null) {
            invoice.setDateCreate(LocalDateTime.now());
        }
        // Các xử lý khác nếu cần
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
