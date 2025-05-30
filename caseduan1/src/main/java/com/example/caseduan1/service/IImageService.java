package com.example.caseduan1.service;



import com.example.caseduan1.model.Image;

import java.util.List;

public interface IImageService {
    List<Image> getAllImages();
    Image getImageById(Long id);
    Image saveImage(Image image);
    Image updateImage(Long id, Image image);
    void deleteImage(Long id);
}
