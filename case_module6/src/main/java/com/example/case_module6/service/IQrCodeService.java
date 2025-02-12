package com.example.case_module6.service;

import com.example.case_module6.model.QRCode;
import com.example.case_module6.model.TableCoffee;


public interface IQrCodeService extends IService<QRCode,Long> {
    TableCoffee findTableCoffeeByQRCodeId(Long id);

}
