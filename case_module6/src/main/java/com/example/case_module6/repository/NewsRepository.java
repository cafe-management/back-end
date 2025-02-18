package com.example.case_module6.repository;

import com.example.case_module6.model.ImageNews;
import com.example.case_module6.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}