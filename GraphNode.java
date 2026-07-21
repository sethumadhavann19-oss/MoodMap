package com.moodmap.model;

/**
 * A location / intersection on the walking map.
 * Coordinates are simple 0-100 grid units used purely for drawing the map on the frontend,
 * not real GPS coordinates.
 */
public class GraphNode {
    private final String id;
    private final String name;
    private final double x;
    private final double y;

    public GraphNode(String id, String name, double x, double y) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getX() { return x; }
    public double getY() { return y; }
}
