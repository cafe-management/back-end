package com.example.case_module6.service;

import com.example.case_module6.model.News;
import com.example.case_module6.model.NewsStatus;

import java.util.List;
import java.util.Optional;

public interface INewsService{
    List<News> findAll(String username, String role, NewsStatus status);
    Optional<News> findById(Long id);
    News save(News news, String username, String role);
    News updateNews(Long id, News newsDetails,  String username, String role);
    void deleteById(Long id);
    List<News> findByStatus(NewsStatus status);
    News updateNewsStatus(Long id, NewsStatus newStatus, String username, String role);
}
