package com.example.case_module6.service.implement;

import com.example.case_module6.model.ImageNews;
import com.example.case_module6.model.News;
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
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public Optional<News> findById(Long id) {
        return newsRepository.findById(id);
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News updateNews(Long id, News newsDetails) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "News not found with id " + id));

        if (newsDetails == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dữ liệu cập nhật không hợp lệ!");
        }

        // Cập nhật tiêu đề và nội dung nếu có
        if (newsDetails.getTitle() != null) {
            existingNews.setTitle(newsDetails.getTitle());
        }
        if (newsDetails.getContent() != null) {
            existingNews.setContent(newsDetails.getContent());
        }

        // Xử lý danh sách hình ảnh:
        // Xóa sạch danh sách ảnh hiện có để đồng bộ hoàn toàn với payload
        existingNews.getImages().clear();

        // Nếu payload có danh sách hình ảnh, thêm từng ảnh vào danh sách
        if (newsDetails.getImages() != null && !newsDetails.getImages().isEmpty()) {
            for (ImageNews image : newsDetails.getImages()) {
                // Đảm bảo set lại mối quan hệ với News
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
}
