package com.vadym.habit_tracker.dto.habit;

import java.time.LocalDate;

public record HabitResponse(Long id,
                            String title,
                            String description,
                            LocalDate createdAt,
                            boolean active
) { }
