package com.smartnav.smartnav_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.dto.IssueRequest;
import com.smartnav.smartnav_backend.service.IssueService;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin("*")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/report")
    public String reportIssue(
            @RequestBody IssueRequest request) {

        issueService.saveIssue(request);

        return "Issue Submitted Successfully";
    }
}