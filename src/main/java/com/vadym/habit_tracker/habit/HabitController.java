package com.vadym.habit_tracker.habit;

import com.vadym.habit_tracker.dto.HabitMapper;
import com.vadym.habit_tracker.dto.habit.HabitRequest;
import com.vadym.habit_tracker.dto.habit.HabitResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitService service;
    private final HabitMapper mapper;

    public HabitController(HabitService service, HabitMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public HabitResponse createHabit(@Valid @RequestBody HabitRequest habitRequest){
        return mapper.toResponse(
                service.createHabit(mapper.toEntity(habitRequest))
        );
    }

    @GetMapping
    public List<HabitResponse> getHabits(){
        return service.getHabits().stream().
                map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public HabitResponse updateHabit(@PathVariable Long id, @Valid @RequestBody HabitRequest request){
        Habit habit = service.updateHabit(id, request);
        return mapper.toResponse(habit);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id){
        service.deleteHabit(id);
    }

}
