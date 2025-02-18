package com.example.case_module6.service;


import com.example.case_module6.model.ImageNews;

import java.util.List;
import java.util.Optional;

public interface INewsImageService {
    List<ImageNews> findAll();
    Optional<ImageNews> findById(Long id);
    ImageNews save(ImageNews imageNews);
    ImageNews updateImage(Long id, ImageNews imageNews);
    void deleteById(Long id);
}
