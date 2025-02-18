package com.example.case_module6.service;

import com.example.case_module6.model.News;

import java.util.List;

public interface IService<E,T> {
    List<E> getAll();
    void save(E entity);
    void update(T id, E entity);
    void delete(T id);
    E findById(T id);
}
