package com.vadym.habit_tracker.dto.user;

import com.vadym.habit_tracker.user.Role;
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
