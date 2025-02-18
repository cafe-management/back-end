package com.example.case_module6.service.implement;

import com.example.case_module6.model.News;
import com.example.case_module6.model.QRCode;
import com.example.case_module6.model.TableCoffee;
import com.example.case_module6.repository.QRCodeRepository;
import com.example.case_module6.service.IQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QrCodeService implements IQrCodeService {

    @Autowired
    private QRCodeRepository qrCodeRepository;
    @Override
    public List<QRCode> getAll() {
        return qrCodeRepository.findAll();
    }

    @Override
    public void save(QRCode entity) {
        qrCodeRepository.save(entity);
    }

    @Override
    public void update(Long id, QRCode entity) {
        if (qrCodeRepository.existsById(id)) {
            entity.setId(id);
            qrCodeRepository.save(entity);
        } else {
            throw new RuntimeException("QRCode với id " + id + " không tồn tại");
        }
    }

    @Override
    public void delete(Long id) {
        qrCodeRepository.deleteById(id);
    }

    @Override
    public QRCode findById(Long id) {
        return qrCodeRepository.findById(id).orElse(null);
    }
    @Override
    public TableCoffee findTableCoffeeByQRCodeId(Long id) {
        QRCode qrCode = qrCodeRepository.findById(id).orElse(null);
        if (qrCode != null) {
            if (qrCode.getTableCoffee() != null) {
                return qrCode.getTableCoffee();
            } else {
                throw new RuntimeException("QRCode với id " + id + " không liên kết với bàn cà phê nào");
            }
        } else {
            throw new RuntimeException("QRCode với id " + id + " không tồn tại");
        }
    }
}
