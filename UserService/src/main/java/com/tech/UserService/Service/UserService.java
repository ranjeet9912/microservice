package com.tech.UserService.Service;

import com.tech.UserService.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(int id);

    User createUser(User user);

    User updateUser(int id, User user);

    boolean deleteUser(int id);
}
