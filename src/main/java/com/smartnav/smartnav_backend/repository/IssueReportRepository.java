package com.smartnav.smartnav_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartnav.smartnav_backend.entity.IssueReport;

public interface IssueReportRepository
        extends JpaRepository<IssueReport, Long> {
}