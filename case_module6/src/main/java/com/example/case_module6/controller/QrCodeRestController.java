package com.example.case_module6.controller;

import com.example.case_module6.model.QRCode;
import com.example.case_module6.model.TableCoffee;
import com.example.case_module6.service.IQrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/qrCode")
public class QrCodeRestController {
    @Autowired
    private IQrCodeService qrCodeService;
    @GetMapping
    public ResponseEntity<List<QRCode>> getAllQRCodes() {
        List<QRCode> qrCodes = qrCodeService.getAll();
        return new ResponseEntity<>(qrCodes, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<QRCode> getQRCodeById(@PathVariable("id") Long id) {
        QRCode qrCode = qrCodeService.findById(id);
        if (qrCode != null) {
            return new ResponseEntity<>(qrCode, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<QRCode> createQRCode(@RequestBody QRCode qrCode) {
        qrCodeService.save(qrCode);
        return new ResponseEntity<>(qrCode, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QRCode> updateQRCode(@PathVariable("id") Long id, @RequestBody QRCode qrCode) {
        try {
            qrCodeService.update(id, qrCode);
            return new ResponseEntity<>(qrCode, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQRCode(@PathVariable("id") Long id) {
        qrCodeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/tableCoffee")
    public ResponseEntity<TableCoffee> getTableCoffeeByQRCodeId(@PathVariable("id") Long id) {
        try {
            TableCoffee tableCoffee = qrCodeService.findTableCoffeeByQRCodeId(id);
            return new ResponseEntity<>(tableCoffee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
