package com.vadym.habit_tracker.dto.habitlog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class HabitLogResponse {
    private Long id;
    private LocalDate date;
    private boolean completed;
}
