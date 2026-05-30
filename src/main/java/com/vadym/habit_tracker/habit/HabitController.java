package com.vadym.habit_tracker.habit;

import com.vadym.habit_tracker.dto.habit.HabitRequest;
import com.vadym.habit_tracker.dto.habit.HabitResponse;
import com.vadym.habit_tracker.dto.streak.StreakResponse;
import com.vadym.habit_tracker.habitlog.HabitLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
@RequiredArgsConstructor
public class HabitController {
    private final HabitService habitService;
    private final HabitLogService habitLogService;

    @PostMapping
    public HabitResponse createHabit(@Valid @RequestBody HabitRequest request){
        return habitService.createHabit(request);
    }

    @GetMapping
    public List<HabitResponse> getHabits(){
        return habitService.getHabits();
    }

    @PutMapping("/{id}")
    public HabitResponse updateHabit(@PathVariable Long id, @Valid @RequestBody HabitRequest request){
        return habitService.updateHabit(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
    }

    @GetMapping("{id}/logs/streak")
    public StreakResponse getStreak(@PathVariable Long id){
        return new StreakResponse(
                habitLogService.getCurrentStreak(id),
                habitLogService.getLongestStreak(id)
        );
    }
}
