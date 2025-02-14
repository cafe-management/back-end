package com.example.case_module6.service;

import com.example.case_module6.model.Image;

import java.util.List;

public interface IImageService {
    List<Image> getAllImages();
    Image getImageById(Long id);
    Image saveImage(Image image);
    Image updateImage(Long id, Image image);
    void deleteImage(Long id);
}
