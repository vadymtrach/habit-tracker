package com.vadym.habit_tracker.dto.user;

import com.vadym.habit_tracker.user.User;

public class UserMapper {
    public User toEntity(UserRequest request){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }

    public UserResponse toResponse(User user){
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }

    public User updateEntity(UserRequest request, User user){
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }
}
