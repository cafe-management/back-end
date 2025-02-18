package com.example.case_module6.controller;

import com.example.case_module6.model.ImageNews;
import com.example.case_module6.service.INewsImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/news/images")
@CrossOrigin(origins = "*")
public class ImageNewsRestController {

    @Autowired
    private INewsImageService imageNewsService;

    // Lấy danh sách tất cả các ảnh
    @GetMapping
    public ResponseEntity<List<ImageNews>> getAllImages() {
        List<ImageNews> images = imageNewsService.findAll();
        return ResponseEntity.ok(images);
    }

    // Lấy thông tin một ảnh theo id
    @GetMapping("/{id}")
    public ResponseEntity<ImageNews> getImageById(@PathVariable Long id) {
        ImageNews image = imageNewsService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ImageNews not found with id " + id));
        return ResponseEntity.ok(image);
    }

    // Tạo mới một ảnh
    @PostMapping
    public ResponseEntity<ImageNews> createImage(@RequestBody ImageNews imageNews) {
        ImageNews createdImage = imageNewsService.save(imageNews);
        return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
    }

    // Cập nhật thông tin một ảnh
    @PutMapping("/{id}")
    public ResponseEntity<ImageNews> updateImage(@PathVariable Long id, @RequestBody ImageNews imageNewsDetails) {
        ImageNews updatedImage = imageNewsService.updateImage(id, imageNewsDetails);
        return ResponseEntity.ok(updatedImage);
    }

    // Xóa một ảnh theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageNewsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
