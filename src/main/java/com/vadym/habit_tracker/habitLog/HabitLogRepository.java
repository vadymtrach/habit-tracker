package com.vadym.habit_tracker.habitLog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    List<HabitLog> findByHabitIdAndDateBetweenOrderByDateDesc(
            Long habitId,
            LocalDate from,
            LocalDate to
    );
    List<HabitLog> findByHabitIdOrderByDateDesc(
            Long habitId
    );
}
