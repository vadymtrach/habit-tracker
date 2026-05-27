package com.vadym.habit_tracker.user;

import com.vadym.habit_tracker.dto.user.UserRequest;
import com.vadym.habit_tracker.dto.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request){
        return userService.createUser(request);
    }

//    @GetMapping("/me")
//    public UserResponse getCurrentUser(){
//
//    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
