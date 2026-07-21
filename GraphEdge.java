package com.moodmap.model;

/**
 * A street segment connecting two nodes. Distance is physical (meters);
 * the other four fields are environmental attributes on a 0.0-1.0 scale
 * that {@link com.moodmap.service.MoodCostCalculator} uses to score the
 * segment for a given target mood.
 *
 *  greenery  - trees / parks / open sky visible along the segment
 *  noise     - traffic / construction / harsh ambient sound
 *  crowd     - pedestrian density
 *  vibrancy  - "good" liveliness: shops, cafés, street life (distinct from noise,
 *              because a lively market street can be loud but pleasant, while a
 *              busy road can be loud but unpleasant)
 */
public class GraphEdge {
    private final String from;
    private final String to;
    private final double distanceMeters;
    private final double greenery;
    private final double noise;
    private final double crowd;
    private final double vibrancy;

    public GraphEdge(String from, String to, double distanceMeters,
                      double greenery, double noise, double crowd, double vibrancy) {
        this.from = from;
        this.to = to;
        this.distanceMeters = distanceMeters;
        this.greenery = greenery;
        this.noise = noise;
        this.crowd = crowd;
        this.vibrancy = vibrancy;
    }

    public String getFrom() { return from; }
    public String getTo() { return to; }
    public double getDistanceMeters() { return distanceMeters; }
    public double getGreenery() { return greenery; }
    public double getNoise() { return noise; }
    public double getCrowd() { return crowd; }
    public double getVibrancy() { return vibrancy; }
}
