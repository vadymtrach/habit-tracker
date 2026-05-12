package com.vadym.habit_tracker.habitLog;

import com.vadym.habit_tracker.habit.Habit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name = "habit_log",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"habit_id", "date"})
        }
)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HabitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;
    @NotNull
    private LocalDate date;
    private boolean completed;
}
