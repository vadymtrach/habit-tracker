package com.vadym.habit_tracker.user.dto;

import com.vadym.habit_tracker.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private boolean active;
}
