package com.example.case_module6.service.implement;

import com.example.case_module6.model.ImageNews;
import com.example.case_module6.model.News;
import com.example.case_module6.model.NewsStatus;
import com.example.case_module6.repository.NewsRepository;
import com.example.case_module6.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class NewService implements INewsService {
    @Autowired
    private NewsRepository newsRepository;
    @Override
    public List<News> findAll(String username, String role, NewsStatus status) {
        if (role.equals("admin")) {
            return newsRepository.findByStatus(status);
        } else if(role.equals("employ")) {
            return newsRepository.findByCreatedBy(username);
        }
        return newsRepository.findByStatus(status);
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public News save(News news, String username, String role) {
        if ("admin".equals(role)) {
            news.setStatus(NewsStatus.APPROVED);
        } else {
            news.setStatus(NewsStatus.PENDING);
        }
        news.setCreatedBy(username); // Gán người tạo bài viết
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(Long id, News newsDetails,  String username, String role) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id));
        if ("admin".equals(role)) {
            return updateExistingNews(existingNews, newsDetails);
        }
        if (newsDetails == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dữ liệu cập nhật không hợp lệ!");
        }
        if (!existingNews.getCreatedBy().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bạn không có quyền sửa tin này!");
        }

        if (existingNews.getStatus() != NewsStatus.PENDING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bạn chỉ có thể chỉnh sửa tin khi đang chờ duyệt!");
        }
        return updateExistingNews(existingNews, newsDetails);
    }
        // Cập nhật tiêu đề và nội dung nếu có

    private News updateExistingNews(News existingNews, News newsDetails) {
        if (newsDetails.getTitle() != null) {
            existingNews.setTitle(newsDetails.getTitle());
        }
        if (newsDetails.getContent() != null) {
            existingNews.setContent(newsDetails.getContent());
        }

        if (newsDetails.getImages() != null && !newsDetails.getImages().isEmpty()) {
            existingNews.getImages().clear();
            for (ImageNews image : newsDetails.getImages()) {
                image.setNews(existingNews);
                existingNews.getImages().add(image);
            }
        }

        return newsRepository.save(existingNews);
    }

    @Override
    public void deleteById(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id);
        }
        newsRepository.deleteById(id);
    }

    @Override
    public List<News> findByStatus(NewsStatus status) {
        return newsRepository.findByStatus(status);
    }

    @Override
    public News updateNewsStatus(Long id, NewsStatus newStatus, String username, String role) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id));

        // Chỉ cho phép admin thay đổi trạng thái bài viết
        if (!"admin".equals(role)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bạn không có quyền thay đổi trạng thái bài viết!");
        }

        existingNews.setStatus(newStatus);
        // Lưu ý: Ở đây không cập nhật lại images hay các trường khác
        return newsRepository.save(existingNews);
    }
}
