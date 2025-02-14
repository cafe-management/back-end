package com.example.case_module6.service.implement;

import com.example.case_module6.model.Invoice;
import com.example.case_module6.repository.InvoiceRepository;
import com.example.case_module6.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice>getAll(){
        return invoiceRepository.findAll();
    }

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void update(Long id, Invoice entity) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        if (invoice.isPresent()) {
            invoice.get().setId(id);
            invoiceRepository.save(entity);
        }
    }

    @Override
    public void delete(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice findById(Long id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoice.orElse(null);
    }
}
