package com.vadym.habit_tracker.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class UserRequest {
    @NotBlank(message = "Username can't be empty")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Must be a valid email format")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 10, message = "Password must be at least 10 characters")
    private String password;
}
