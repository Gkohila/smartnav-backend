package com.smartnav.smartnav_backend.controller;

import com.smartnav.smartnav_backend.entity.SearchHistory;
import com.smartnav.smartnav_backend.service.SearchHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "*")
public class SearchHistoryController {

    private final SearchHistoryService service;

    public SearchHistoryController(SearchHistoryService service) {
        this.service = service;
    }

    @PostMapping
    public SearchHistory saveHistory(
            @RequestBody SearchHistory history) {

        return service.save(history);
    }

    @GetMapping
    public List<SearchHistory> getHistory() {
        return service.getAllHistory();
    }

    @GetMapping("/user/{userId}")
    public List<SearchHistory> getHistoryByUser(
            @PathVariable Long userId) {

        return service.getHistoryByUser(userId);
    }

    @DeleteMapping
    public String clearHistory() {

        service.deleteAll();

        return "History Cleared";
    }

    @DeleteMapping("/user/{userId}")
public String deleteUserHistory(@PathVariable Long userId) {

    service.deleteHistoryByUser(userId);

    return "History Deleted";
}
}