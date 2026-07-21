package com.moodmap.controller;

import com.moodmap.dto.Dtos.RouteRequestDTO;
import com.moodmap.dto.Dtos.RouteResponseDTO;
import com.moodmap.model.Mood;
import com.moodmap.model.UserPreference;
import com.moodmap.service.DijkstraService;
import com.moodmap.service.FeedbackService;
import com.moodmap.service.GraphService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final DijkstraService dijkstraService;
    private final GraphService graphService;
    private final FeedbackService feedbackService;

    public RouteController(DijkstraService dijkstraService, GraphService graphService,
                            FeedbackService feedbackService) {
        this.dijkstraService = dijkstraService;
        this.graphService = graphService;
        this.feedbackService = feedbackService;
    }

    @PostMapping
    public RouteResponseDTO getRoute(@RequestBody RouteRequestDTO request) {
        if (!graphService.hasNode(request.startNodeId()) || !graphService.hasNode(request.endNodeId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown start or end node");
        }

        Mood currentMood;
        Mood targetMood;
        try {
            currentMood = Mood.valueOf(request.currentMood().toUpperCase());
            targetMood = Mood.valueOf(request.targetMood().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "currentMood must be ANXIOUS/TIRED/NEUTRAL/HAPPY and targetMood must be CALM/FOCUSED/ENERGIZED/HAPPY");
        }

        String userId = (request.userId() == null || request.userId().isBlank()) ? "default" : request.userId();
        UserPreference prefs = feedbackService.getOrCreatePreferences(userId);

        return dijkstraService.findRoute(request.startNodeId(), request.endNodeId(),
                currentMood, targetMood, prefs);
    }
}
