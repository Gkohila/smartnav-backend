package com.smartnav.smartnav_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartnav.smartnav_backend.dto.FeedbackRequest;
import com.smartnav.smartnav_backend.service.FeedbackService;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @PostMapping("/submit")
    public String submitFeedback(
            @RequestBody FeedbackRequest request) {

        service.saveFeedback(request);

        return "Feedback Submitted Successfully";
    }
}