package com.example.case_module6.repository;


import com.example.case_module6.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedBackRepository extends JpaRepository<Feedback,Long> {
}
