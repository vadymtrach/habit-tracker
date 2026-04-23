package com.vadym.habit_tracker.habitLog;

import com.vadym.habit_tracker.habit.Habit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "habit_log",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"habit_id", "date"})
        }
)
public class HabitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Getter
    @ManyToOne @JoinColumn(name = "habit_id")
    private Habit habit;

    private LocalDate date;
    private boolean completed;
}
