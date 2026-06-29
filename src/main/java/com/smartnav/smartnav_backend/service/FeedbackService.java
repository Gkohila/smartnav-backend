package com.smartnav.smartnav_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartnav.smartnav_backend.dto.FeedbackRequest;
import com.smartnav.smartnav_backend.entity.Feedback;
import com.smartnav.smartnav_backend.repository.FeedbackRepository;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    public void saveFeedback(
            FeedbackRequest request) {

        Feedback feedback = new Feedback();

        feedback.setCategory(
                request.getCategory());

        feedback.setFeedback(
                request.getFeedback());

        repository.save(feedback);
    }
}