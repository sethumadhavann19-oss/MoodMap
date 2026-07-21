package com.moodmap.controller;

import com.moodmap.dto.Dtos.FeedbackRequestDTO;
import com.moodmap.model.Mood;
import com.moodmap.model.UserPreference;
import com.moodmap.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public UserPreference submitFeedback(@RequestBody FeedbackRequestDTO request) {
        Mood targetMood;
        try {
            targetMood = Mood.valueOf(request.targetMood().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid targetMood");
        }
        if (request.feltRating() < -2 || request.feltRating() > 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "feltRating must be between -2 and 2");
        }

        String userId = (request.userId() == null || request.userId().isBlank()) ? "default" : request.userId();
        return feedbackService.applyFeedback(userId, request.path(), targetMood, request.feltRating());
    }
}
