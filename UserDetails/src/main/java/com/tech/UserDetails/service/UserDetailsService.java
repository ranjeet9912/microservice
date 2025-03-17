package com.tech.UserDetails.service;
import com.tech.UserDetails.entity.UserDetails;
import com.tech.UserDetails.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Cacheable(value = "userDetails")
    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    @Cacheable(value = "userDetail", key = "#id")
    public Optional<UserDetails> getUserDetailsById(int id) {
        return userDetailsRepository.findById(id);
    }

    @CachePut(value = "userDetail", key = "#userDetails.id")
    @CacheEvict(value = "userDetails", allEntries = true)
    public UserDetails createUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    @CachePut(value = "userDetail", key = "#id")
    @CacheEvict(value = "userDetails", allEntries = true)
    public UserDetails updateUserDetails(int id, UserDetails userDetails) {
        return userDetailsRepository.findById(id).map(existing -> {
           // existing.setUserId(userDetails.getUserId());
            existing.setAddress(userDetails.getAddress());
            existing.setMobileNumber(userDetails.getMobileNumber());
            return userDetailsRepository.save(existing);
        }).orElse(null);
    }

    @CacheEvict(value = "userDetail", key = "#id")
    public boolean deleteUserDetails(int id) {
        if (userDetailsRepository.existsById(id)) {
            userDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
