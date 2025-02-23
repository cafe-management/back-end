package com.example.case_module6.repository;

import com.example.case_module6.model.ImageNews;
import com.example.case_module6.model.News;
import com.example.case_module6.model.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByCreatedBy(String createdBy);
    List<News> findByStatus(NewsStatus status);
}