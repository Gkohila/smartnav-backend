package com.smartnav.smartnav_backend.service;

import com.smartnav.smartnav_backend.entity.SearchHistory;
import com.smartnav.smartnav_backend.repository.SearchHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Service
public class SearchHistoryService {

    private final SearchHistoryRepository repository;

    public SearchHistoryService(SearchHistoryRepository repository) {
        this.repository = repository;
    }

    public SearchHistory save(SearchHistory history) {

        history.setSearchedAt(LocalDateTime.now());

        return repository.save(history);
    }

    public List<SearchHistory> getAllHistory() {
        return repository.findAll();
    }
    
    public List<SearchHistory> getHistoryByUser(Long userId) {
    return repository.findByUserId(userId);
}

    public void deleteAll() {
        repository.deleteAll();
    }

   @Transactional
public void deleteHistoryByUser(Long userId) {
    repository.deleteByUserId(userId);
}
}