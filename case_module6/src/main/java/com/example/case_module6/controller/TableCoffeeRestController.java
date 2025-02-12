package com.example.case_module6.controller;

import com.example.case_module6.model.TableCoffee;
import com.example.case_module6.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tableCoffee")
public class TableCoffeeRestController {

    @Autowired
    private ITableService tableService;

    @GetMapping
    public ResponseEntity<List<TableCoffee>>getAllTableCoffees() {
        List<TableCoffee> tableCoffeeList = tableService.getAll();
        return new ResponseEntity<>(tableCoffeeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableCoffee> getTableCoffeeById(@PathVariable("id") Long id) {
        TableCoffee tableCoffee = tableService.findById(id);
        if (tableCoffee != null) {
            return new ResponseEntity<>(tableCoffee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<TableCoffee> createTableCoffee(@RequestBody TableCoffee tableCoffee) {
        tableService.save(tableCoffee);
        return new ResponseEntity<>(tableCoffee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TableCoffee> updateTableCoffee(@PathVariable("id") Long id,
                                                         @RequestBody TableCoffee tableCoffee) {
        try {
            tableService.update(id, tableCoffee);
            return new ResponseEntity<>(tableCoffee, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTableCoffee(@PathVariable("id") Long id) {
        tableService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
