package com.vadym.habit_tracker.user.repository;

import com.vadym.habit_tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
