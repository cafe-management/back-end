package com.example.case_module6.repository;

import com.example.case_module6.model.TableCoffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableCoffeeRepository extends JpaRepository<TableCoffee, Long> {
}
