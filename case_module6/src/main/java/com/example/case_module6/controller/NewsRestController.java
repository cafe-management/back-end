package com.example.case_module6.controller;

import com.example.case_module6.model.News;
import com.example.case_module6.model.NewsStatus;
import com.example.case_module6.model.User;
import com.example.case_module6.service.INewsService;
import com.example.case_module6.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsRestController {

    @Autowired
    private INewsService newsService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private IUserService userService;
    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        List<News> newsList = newsService.findAll(username, role);
        return ResponseEntity.ok(newsList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        News news = newsService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id));
        return ResponseEntity.ok(news);
    }

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody News news) {
        try {
            if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("⚠️ Tiêu đề tin tức không được để trống!");
            }
            if (news.getContent() == null || news.getContent().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("⚠️ Nội dung tin tức không được để trống!");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String role = authentication.getAuthorities().iterator().next().getAuthority();

            News createdNews = newsService.save(news, username, role);
            if (messagingTemplate != null) {
                messagingTemplate.convertAndSend("/topic/news", "📰 Tin tức mới: " + createdNews.getTitle());
                messagingTemplate.convertAndSend("/topic/notifications", "📢 Bài viết mới được đăng: " + createdNews.getTitle());
            }
            return new ResponseEntity<>(createdNews, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Lỗi khi tạo tin tức: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@PathVariable Long id, @RequestBody News newsDetails, @RequestParam String username,
                                        @RequestParam String role) {
        try {
            // Kiểm tra dữ liệu bắt buộc: tiêu đề và nội dung không được để trống
            if (newsDetails.getTitle() == null || newsDetails.getTitle().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("⚠️ Tiêu đề tin tức không được để trống!");
            }
            if (newsDetails.getContent() == null || newsDetails.getContent().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("⚠️ Nội dung tin tức không được để trống!");
            }

            // Gọi hàm cập nhật trong service (đã được điều chỉnh để xử lý danh sách ảnh)
            News updatedNews = newsService.updateNews(id, newsDetails, username, role);

            // Gửi thông báo nếu có cấu hình SimpMessagingTemplate
            if (messagingTemplate != null) {
                messagingTemplate.convertAndSend("/topic/news", "✍️ Tin tức được cập nhật: " + updatedNews.getTitle());
                messagingTemplate.convertAndSend("/topic/notifications", "🛠️ Một bài viết đã được cập nhật: " + updatedNews.getTitle());
            }
            return ResponseEntity.ok(updatedNews);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Lỗi khi cập nhật tin tức: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id) {
        try {
            newsService.deleteById(id);
            if (messagingTemplate != null) {
                messagingTemplate.convertAndSend("/topic/notifications", "❌ Một bài viết đã bị xóa: ID " + id);
            }
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("❌ Lỗi khi xóa tin tức: " + e.getMessage());
        }
    }
    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approveNews(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Lấy username từ token đã xác thực
        User user = userService.getUserByUsername(username);


        if (user == null || !user.getAccount().getRole().equals("admin")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền duyệt bài!");
        }
        Optional<News> optionalNews = newsService.findById(id);
        if (optionalNews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy bài viết!");
        }
        News news = optionalNews.get();

        news.setStatus(NewsStatus.APPROVED);
        newsService.updateNews(id, news, username, user.getAccount().getRole().getNameRoles());
        return ResponseEntity.ok("Bài viết đã được duyệt!");
    }

}
