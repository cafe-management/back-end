package com.example.case_module6.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IService<E,T> {
    List<E> getAll();
    void save(E entity);
    void update(T id, E entity);
    void delete(T id);
    E findById(T id);
}
