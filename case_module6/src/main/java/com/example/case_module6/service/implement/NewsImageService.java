package com.example.case_module6.service.implement;

import com.example.case_module6.model.Image;
import com.example.case_module6.model.ImageNews;
import com.example.case_module6.repository.INewsImageRepository;
import com.example.case_module6.repository.ImageRepository;
import com.example.case_module6.service.IImageService;
import com.example.case_module6.service.INewsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewsImageService implements INewsImageService {
    @Autowired
    private INewsImageRepository imageNewsRepository;

    @Override
    public List<ImageNews> findAll() {
        return imageNewsRepository.findAll();
    }

    @Override
    public Optional<ImageNews> findById(Long id) {
        return imageNewsRepository.findById(id);
    }

    @Override
    public ImageNews save(ImageNews imageNews) {
        return imageNewsRepository.save(imageNews);
    }

    @Override
    public ImageNews updateImage(Long id, ImageNews imageNewsDetails) {
        ImageNews existingImage = imageNewsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ImageNews not found with id " + id));

        existingImage.setImg(imageNewsDetails.getImg());
        return imageNewsRepository.save(existingImage);
    }

    @Override
    public void deleteById(Long id) {
        if (!imageNewsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ImageNews not found with id " + id);
        }
        imageNewsRepository.deleteById(id);
    }
}
