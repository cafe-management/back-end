package com.example.case_module6.service.implement;

import com.example.case_module6.model.News;
import com.example.case_module6.model.TableCoffee;
import com.example.case_module6.repository.TableCoffeeRepository;
import com.example.case_module6.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableCoffeeService implements ITableService {
    @Autowired
    private TableCoffeeRepository tableCoffeeRepository;

    @Override
    public List<TableCoffee> getAll() {
        return tableCoffeeRepository.findAll();
    }

    @Override
    public void save(TableCoffee entity) {
        tableCoffeeRepository.save(entity);
    }

    @Override
    public void update(Long id, TableCoffee entity) {
        if (tableCoffeeRepository.existsById(id)) {
            entity.setId(id);
            tableCoffeeRepository.save(entity);
        } else {
            throw new RuntimeException("TableCoffee with id " + id + " does not exist");
        }
    }

    @Override
    public void delete(Long id) {
        tableCoffeeRepository.deleteById(id);
    }

    @Override
    public TableCoffee findById(Long id) {
        return tableCoffeeRepository.findById(id).orElse(null);
    }

    @Override
    public TableCoffee updateStatus(Long id, Integer newStatus, String token) {
        Optional<TableCoffee> optionalTable = tableCoffeeRepository.findById(id);
        if (!optionalTable.isPresent()) {
            throw new RuntimeException("TableCoffee with id " + id + " does not exist");
        }
        TableCoffee tableCoffee = optionalTable.get();
        tableCoffee.setStatusTable(newStatus);

        // Nếu newStatus là 1 (bàn được sử dụng), cập nhật token, ngược lại xóa token (hoặc để lại null)
        if(newStatus == 1) {
            tableCoffee.setToken(token);
        } else {
            tableCoffee.setToken(null);
        }

        return tableCoffeeRepository.save(tableCoffee);
    }

}
