package com.vadym.habit_tracker.habitlog.service;

import com.vadym.habit_tracker.common.exception.NotFoundException;
import com.vadym.habit_tracker.habitlog.mapper.HabitLogMapper;
import com.vadym.habit_tracker.habitlog.dto.HabitLogRequest;
import com.vadym.habit_tracker.habitlog.dto.HabitLogResponse;
import com.vadym.habit_tracker.habit.entity.Habit;
import com.vadym.habit_tracker.habit.repository.HabitRepository;
import com.vadym.habit_tracker.habitlog.entity.HabitLog;
import com.vadym.habit_tracker.habitlog.repository.HabitLogRepository;
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

    public int getCurrentStreak(Long habitId) {
        List<HabitLog> logs = habitLogRepository.findByHabitIdOrderByDateDesc(habitId);
        int counter = 0;
        LocalDate expected = LocalDate.now();

        for (HabitLog log : logs) {
            if (log.getDate().equals(expected) && log.isCompleted()) {
                counter++;
                expected = expected.minusDays(1);
            } else {
                break;
            }
        }
        return counter;
    }

    public int getLongestStreak(Long habitId) {
        List<HabitLog> logs = habitLogRepository.findByHabitIdOrderByDateDesc(habitId);
        int current = 0, longest = 0;
        LocalDate expected = null;

        for (HabitLog log : logs) {
            if (expected == null || log.getDate().equals(expected) && log.isCompleted()) {
                current++;
                longest = Math.max(current, longest);
                expected = log.getDate().minusDays(1);
            } else if (!log.getDate().equals(expected) || !log.isCompleted()) {
                current = log.isCompleted() ? 1 : 0;
                expected = log.isCompleted() ? log.getDate().minusDays(1) : null;
            }
        }
        return longest;
    }
}
