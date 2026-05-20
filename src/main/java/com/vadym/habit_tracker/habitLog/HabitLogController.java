package com.vadym.habit_tracker.habitLog;

import com.vadym.habit_tracker.dto.habitLog.HabitLogRequest;
import com.vadym.habit_tracker.dto.habitLog.HabitLogResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/habits/{id}/logs")
@RequiredArgsConstructor
public class HabitLogController {
    private final HabitLogService service;
    @PostMapping
    public HabitLogResponse createHabitLog(@PathVariable Long id,
                                           @RequestBody @Valid HabitLogRequest request){
        return service.createHabitLog(id, request);
    }
    @GetMapping
    public List<HabitLogResponse> getHabitHistory(@PathVariable Long id,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to){
        return service.getHabitHistory(id, from, to);
    }

}
