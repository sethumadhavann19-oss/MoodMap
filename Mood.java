package com.moodmap.model;

/**
 * Emotional states a user can start from or aim for.
 * Each TARGET mood (CALM, FOCUSED, ENERGIZED, HAPPY) has an ideal
 * environmental profile used by {@link com.moodmap.service.MoodCostCalculator}.
 */
public enum Mood {
    // Starting moods
    ANXIOUS,
    TIRED,
    NEUTRAL,
    HAPPY,

    // Target moods
    CALM,
    FOCUSED,
    ENERGIZED
    // HAPPY is reused as both a starting and target mood
}
