package com.example.case_module6.controller;

import com.example.case_module6.model.News;
import com.example.case_module6.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsRestController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.findAll();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        News news = newsService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id));
        return ResponseEntity.ok(news);
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News news) {
        News createdNews = newsService.save(news);
        messagingTemplate.convertAndSend("/topic/news", "📰 Tin tức mới: " + createdNews.getTitle());
        messagingTemplate.convertAndSend("/topic/notifications", "📢 Bài viết mới được đăng: " + createdNews.getTitle());
        return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        News updatedNews = newsService.updateNews(id, newsDetails);
        messagingTemplate.convertAndSend("/topic/news", "✍️ Tin tức được cập nhật: " + updatedNews.getTitle());
        messagingTemplate.convertAndSend("/topic/notifications", "🛠️ Một bài viết đã được cập nhật: " + updatedNews.getTitle());
        return ResponseEntity.ok(updatedNews);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteById(id);
        messagingTemplate.convertAndSend("/topic/notifications", "❌ Một bài viết đã bị xóa: ID " + id);
        return ResponseEntity.noContent().build();
    }
}
