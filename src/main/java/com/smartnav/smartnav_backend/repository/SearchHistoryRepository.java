package com.smartnav.smartnav_backend.repository;

import com.smartnav.smartnav_backend.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SearchHistoryRepository
        extends JpaRepository<SearchHistory, Long> {

   List<SearchHistory> findByUserId(Long userId);

@Transactional
@Modifying
@Query("DELETE FROM SearchHistory s WHERE s.userId = :userId")
void deleteByUserId(Long userId);
}