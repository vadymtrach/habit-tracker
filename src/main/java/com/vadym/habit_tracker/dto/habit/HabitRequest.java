package com.vadym.habit_tracker.dto.habit;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HabitRequest(

        @NotNull(message = "Title can't be null")
        @Size(min = 1, max = 50, message =
                "Title must be between 1 and 50 characters")
        String title,

        @Size(max = 255, message = "Description must be max 255 characters")
        String description) {

}
