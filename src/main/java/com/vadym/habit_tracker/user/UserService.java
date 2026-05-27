package com.vadym.habit_tracker.user;

import com.vadym.habit_tracker.common.exception.NotFoundException;
import com.vadym.habit_tracker.dto.habit.HabitRequest;
import com.vadym.habit_tracker.dto.habit.HabitResponse;
import com.vadym.habit_tracker.dto.user.UserMapper;
import com.vadym.habit_tracker.dto.user.UserRequest;
import com.vadym.habit_tracker.dto.user.UserResponse;
import com.vadym.habit_tracker.habit.Habit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest request){
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.USER);

        return userMapper.toResponse(userRepository.save(user));
    }

    public User getUserEntity(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found " + id));
    }

    public UserResponse getUser(Long id){
        return userMapper.toResponse(getUserEntity(id));
    }

    public UserResponse updateUser(Long id, UserRequest request){
        User user = getUserEntity(id);
        userMapper.updateEntity(request, user);

        return userMapper.toResponse(userRepository.save(user));
    }

}
