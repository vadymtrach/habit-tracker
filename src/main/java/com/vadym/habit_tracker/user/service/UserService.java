package com.vadym.habit_tracker.user.service;

import com.vadym.habit_tracker.common.exception.NotFoundException;
import com.vadym.habit_tracker.user.mapper.UserMapper;
import com.vadym.habit_tracker.user.dto.UserRequest;
import com.vadym.habit_tracker.user.dto.UserResponse;
import com.vadym.habit_tracker.user.entity.Role;
import com.vadym.habit_tracker.user.entity.User;
import com.vadym.habit_tracker.user.repository.UserRepository;
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

    public void deleteUser(Long id){
        User user = getUserEntity(id);
        if (!user.isActive()){
            return;
        }
        user.setActive(false);
        userRepository.save(user);
    }
}
