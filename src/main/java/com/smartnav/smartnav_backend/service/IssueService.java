package com.smartnav.smartnav_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartnav.smartnav_backend.dto.IssueRequest;
import com.smartnav.smartnav_backend.entity.IssueReport;
import com.smartnav.smartnav_backend.repository.IssueReportRepository;

@Service
public class IssueService {

    @Autowired
    private IssueReportRepository issueReportRepository;

    public void saveIssue(IssueRequest request) {

        IssueReport issue = new IssueReport();

        issue.setIssueTitle(
                request.getIssueTitle());

        issue.setCategory(
                request.getCategory());

        issue.setDescription(
                request.getDescription());

        issueReportRepository.save(issue);
    }
}