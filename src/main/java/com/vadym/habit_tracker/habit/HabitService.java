package com.vadym.habit_tracker.habit;

import com.vadym.habit_tracker.common.exception.NotFoundException;
import com.vadym.habit_tracker.dto.habit.HabitMapper;
import com.vadym.habit_tracker.dto.habit.HabitRequest;
import com.vadym.habit_tracker.dto.habit.HabitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository repository;
    private final HabitMapper mapper;

    public HabitResponse createHabit(HabitRequest request){
        Habit habit = mapper.toEntity(request);

        habit.setActive(true);
        habit.setCreatedAt(LocalDate.now());

        return mapper.toResponse(repository.save(habit));
    }

    public List<HabitResponse> getHabits(){
        return repository.findByActiveTrue().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Habit getHabitEntity(Long id){
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException("Habit not found " + id));
    }

    public HabitResponse getHabit(Long id){
        return mapper.toResponse(getHabitEntity(id));
    }

    public void deleteHabit(Long id){
        Habit habit = getHabitEntity(id);

        if (!habit.isActive()){
            return;
        }

        habit.setActive(false);
        repository.save(habit);
    }

    public HabitResponse updateHabit(Long id, HabitRequest request){
        Habit habit = getHabitEntity(id);
        mapper.updateEntity(request, habit);

        return mapper.toResponse(repository.save(habit));
    }
}
