package com.moodmap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Learned personal weighting for a user, persisted in H2.
 * These multipliers start at 1.0 (neutral) and are nudged up or down by
 * {@link com.moodmap.service.FeedbackService} after each trip based on how the
 * user actually felt versus what the route predicted.
 *
 * Example: if a user keeps rating "calm" routes as unsuccessful when the route
 * still had high noise, noiseSensitivity increases, so future Dijkstra runs
 * penalise noisy edges more heavily for that user.
 */
@Entity
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private double greenerySensitivity = 1.0;
    private double noiseSensitivity = 1.0;
    private double crowdSensitivity = 1.0;
    private double vibrancySensitivity = 1.0;

    public UserPreference() {}

    public UserPreference(String userId) {
        this.userId = userId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public double getGreenerySensitivity() { return greenerySensitivity; }
    public void setGreenerySensitivity(double v) { this.greenerySensitivity = v; }

    public double getNoiseSensitivity() { return noiseSensitivity; }
    public void setNoiseSensitivity(double v) { this.noiseSensitivity = v; }

    public double getCrowdSensitivity() { return crowdSensitivity; }
    public void setCrowdSensitivity(double v) { this.crowdSensitivity = v; }

    public double getVibrancySensitivity() { return vibrancySensitivity; }
    public void setVibrancySensitivity(double v) { this.vibrancySensitivity = v; }
}
