package com.moodmap.controller;

import com.moodmap.dto.Dtos.EdgeDTO;
import com.moodmap.dto.Dtos.GraphDTO;
import com.moodmap.dto.Dtos.NodeDTO;
import com.moodmap.model.GraphEdge;
import com.moodmap.model.GraphNode;
import com.moodmap.service.GraphService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping
    public GraphDTO getGraph() {
        List<NodeDTO> nodes = graphService.getNodes().stream()
                .map(n -> new NodeDTO(n.getId(), n.getName(), n.getX(), n.getY()))
                .toList();

        List<EdgeDTO> edges = graphService.getEdges().stream()
                .map(e -> new EdgeDTO(e.getFrom(), e.getTo(), e.getDistanceMeters(),
                        e.getGreenery(), e.getNoise(), e.getCrowd(), e.getVibrancy()))
                .toList();

        return new GraphDTO(nodes, edges);
    }
}
