package com.vadym.habit_tracker.dto;

import com.vadym.habit_tracker.dto.habit.HabitRequest;
import com.vadym.habit_tracker.dto.habit.HabitResponse;
import com.vadym.habit_tracker.habit.Habit;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {
    public HabitResponse toResponse(Habit habit){
        return new HabitResponse(
                habit.getId(),
                habit.getTitle(),
                habit.getDescription(),
                habit.getCreatedAt(),
                habit.isActive());
    }

    public Habit toEntity(HabitRequest habitRequest){
        Habit habit = new Habit();
        habit.setTitle(habitRequest.title());
        habit.setDescription(habitRequest.description());

        return habit;
    }

    public void updateEntity(HabitRequest request, Habit habit){
        habit.setTitle(request.title());
        habit.setDescription(request.description());
    }
}
