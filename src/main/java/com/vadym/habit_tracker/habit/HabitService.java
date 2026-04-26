package com.vadym.habit_tracker.habit;

import com.vadym.habit_tracker.dto.HabitMapper;
import com.vadym.habit_tracker.dto.habit.HabitRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitService {
    private final HabitRepository repository;
    private final HabitMapper mapper;

    public HabitService(HabitRepository repository, HabitMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
        Habit habit = getHabit(id);

        if (!habit.isActive()){
            return;
        }

        habit.setActive(false);
        repository.save(habit);
    }

    public Habit updateHabit(Long id, HabitRequest request){
        Habit habit = getHabit(id);
        mapper.updateEntity(request, habit);

        return repository.save(habit);
    }
}
