package com.example.case_module6.service.implement;

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
        existingNews.setTitle(newsDetails.getTitle());
        existingNews.setContent(newsDetails.getContent());
        if (newsDetails.getImages() != null && !newsDetails.getImages().isEmpty()) {
            existingNews.getImages().clear();
            existingNews.getImages().addAll(newsDetails.getImages());
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
