package com.vadym.habit_tracker.habit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitService service;

    public HabitController(HabitService service) {
        this.service = service;
    }

    @PostMapping("")
    public Habit createHabit(@RequestBody Habit habit){
        return service.createHabit(habit);
    }

    @GetMapping("")
    public List<Habit> getHabits(){
        return service.getHabits();
    }

    @PutMapping("/{id}")
    public Habit updateHabit(@PathVariable Long id, @RequestBody Habit habit){
        Habit habitDb = service.getHabit(id);

        habitDb.setTitle(habit.getTitle());
        habitDb.setDescription(habit.getDescription());

        return service.createHabit(habitDb);
    }

    @DeleteMapping("/{id}")
    public void deleteHabit(@PathVariable Long id){
        service.deleteHabit(id);
    }

}
