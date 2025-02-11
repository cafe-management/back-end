package com.example.case_module6.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IService<E> {
    List<E> getAll();
    void save(E entity);
    void update(int id, E entity);
    void delete(int id);
    E findById(int id);
}
