package com.tech.UserService.Service;

import com.tech.UserService.Entity.User;
import com.tech.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(value = "users") // Caches the list of users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "user", key = "#id") // Caches user by ID
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    @CachePut(value = "user", key = "#user.id") // Updates cache when a new user is created
    @CacheEvict(value = "users", allEntries = true) // Clears the users list cache
    public User createUser(User user) {
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        return userRepository.save(user);
    }

    @Override
    @CachePut(value = "user", key = "#id") // Updates cache when a user is updated
    @CacheEvict(value = "users", allEntries = true) // Clears the users list cache
    public User updateUser(int id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    @CacheEvict(value = "user", key = "#id") // Removes user from cache when deleted
    // @CacheEvict(value = "users", allEntries = true) // Clears the users list cache
    public boolean deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}


