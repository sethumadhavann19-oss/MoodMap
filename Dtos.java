package com.moodmap.dto;

import java.util.List;

public class Dtos {

    public record NodeDTO(String id, String name, double x, double y) {}

    public record EdgeDTO(String from, String to, double distanceMeters,
                           double greenery, double noise, double crowd, double vibrancy) {}

    public record GraphDTO(List<NodeDTO> nodes, List<EdgeDTO> edges) {}

    public record RouteRequestDTO(String userId, String startNodeId, String endNodeId,
                                   String currentMood, String targetMood) {}

    public record RouteEdgeDetail(String from, String to, double distanceMeters, double cost,
                                   double greenery, double noise, double crowd, double vibrancy) {}

    public record RouteResponseDTO(List<String> path, double totalDistanceMeters,
                                    double totalCost, double moodFitScore,
                                    List<RouteEdgeDetail> segments) {}

    public record FeedbackRequestDTO(String userId, List<String> path,
                                      String targetMood, int feltRating) {}
    // feltRating: -2 (much worse than expected) .. +2 (much better than expected), 0 = as expected
}
