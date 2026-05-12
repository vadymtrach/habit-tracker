package com.vadym.habit_tracker.habitLog;

import com.vadym.habit_tracker.common.exception.NotFoundException;
import com.vadym.habit_tracker.dto.HabitLogMapper;
import com.vadym.habit_tracker.dto.habitLog.HabitLogRequest;
import com.vadym.habit_tracker.dto.habitLog.HabitLogResponse;
import com.vadym.habit_tracker.habit.Habit;
import com.vadym.habit_tracker.habit.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitLogService {
    private final HabitLogRepository habitLogRepository;
    private final HabitRepository habitRepository;
    private final HabitLogMapper mapper;

    public HabitLogResponse createHabitLog(Long habitId,
                                           HabitLogRequest request){
        HabitLog log = mapper.toEntity(request);
        Habit habit = habitRepository.findById(habitId).orElseThrow(
                () -> new NotFoundException("Habit not found" + habitId));
        log.setHabit(habit);
        try{
            return mapper.toResponse(habitLogRepository.save(log));
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("A log for this habit on this date already exists.", e);
        }
    }

    public List<HabitLogResponse> getHabitHistory(Long habitId, LocalDate from, LocalDate to){
        if (habitRepository.existsById(habitId)){
            return habitLogRepository.findByHabitIdAndDateBetweenOrderByDateDesc(habitId, from, to)
                    .stream()
                    .map(mapper::toResponse)
                    .toList();
        }
        throw new NotFoundException("Habit not found " + habitId);

    }

}
