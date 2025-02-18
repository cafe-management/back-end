package com.example.case_module6.service;

import com.example.case_module6.model.News;

import java.util.List;
import java.util.Optional;

public interface INewsService{
    List<News> findAll();
    Optional<News> findById(Long id);
    News save(News news);
    News updateNews(Long id, News newsDetails);
    void deleteById(Long id);
}
