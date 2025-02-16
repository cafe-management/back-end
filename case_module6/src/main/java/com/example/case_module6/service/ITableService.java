package com.example.case_module6.service;

import com.example.case_module6.model.TableCoffee;

public interface ITableService extends IService<TableCoffee,Long> {
    TableCoffee updateStatus(Long id,Integer status,String token);
}
