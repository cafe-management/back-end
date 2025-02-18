package com.example.case_module6.repository;

import com.example.case_module6.model.ImageNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INewsImageRepository extends JpaRepository<ImageNews , Long> {
}
