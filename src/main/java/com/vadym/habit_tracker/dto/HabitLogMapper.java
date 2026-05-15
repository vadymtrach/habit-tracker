package com.vadym.habit_tracker.dto;

import com.vadym.habit_tracker.dto.habitLog.HabitLogRequest;
import com.vadym.habit_tracker.dto.habitLog.HabitLogResponse;
import com.vadym.habit_tracker.habitLog.HabitLog;
import org.springframework.stereotype.Component;

@Component
public class HabitLogMapper {
    public HabitLogResponse toResponse(HabitLog log){
        return new HabitLogResponse(log.getId(),
                log.getDate(),
                log.isCompleted());
    }

    public HabitLog toEntity(HabitLogRequest request){
        HabitLog log = new HabitLog();

        log.setDate(request.getDate());
        log.setCompleted(request.isCompleted());

        return log;
    }

    public void updateEntity(HabitLog request, HabitLog log){
        log.setDate(request.getDate());
        log.setCompleted(request.isCompleted());
    }
}
