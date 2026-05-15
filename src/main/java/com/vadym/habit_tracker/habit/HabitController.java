package com.vadym.habit_tracker.habit;

import com.vadym.habit_tracker.dto.habit.HabitRequest;
import com.vadym.habit_tracker.dto.habit.HabitResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class HabitController {
    private final HabitService service;

    @PostMapping
    public HabitResponse createHabit(@Valid @RequestBody HabitRequest request){
        return service.createHabit(request);
    }

    @GetMapping
    public List<HabitResponse> getHabits(){
        return service.getHabits();
    }

    @PutMapping("/{id}")
    public HabitResponse updateHabit(@PathVariable Long id, @Valid @RequestBody HabitRequest request){
        return service.updateHabit(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id){
        service.deleteHabit(id);
    }

    //@GetMapping("{id}/streak")
}
