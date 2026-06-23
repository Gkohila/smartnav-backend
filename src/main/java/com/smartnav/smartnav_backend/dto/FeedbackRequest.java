package com.smartnav.smartnav_backend.dto;

public class FeedbackRequest {

    private String category;
    private String feedback;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}