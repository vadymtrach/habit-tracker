package com.vadym.habit_tracker.habitLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    List<HabitLog> findByHabitIdAndDateBetweenOrderByDateDesc(
            Long HabitId,
            LocalDate from,
            LocalDate to
    );
}
