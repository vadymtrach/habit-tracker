package com.vadym.habit_tracker.habit;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitService {
    private final HabitRepository repository;

    public HabitService(HabitRepository repository) {
        this.repository = repository;
    }

    public Habit createHabit(Habit habit){
        habit.setActive(true);
        habit.setCreatedAt(LocalDate.now());
        return repository.save(habit);
    }

    public List<Habit> getHabits(){
        return repository.findByActiveTrue();
    }

    public Habit getHabit(Long id){
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Habit not found " + id));
    }

    public void deleteHabit(Long id){
        Habit habit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found: " + id));

        if (!habit.isActive()){
            return;
        }

        habit.setActive(false);
        repository.save(habit);
    }
}
