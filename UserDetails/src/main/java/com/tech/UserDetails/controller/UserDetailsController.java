
package com.tech.UserDetails.controller;


import com.tech.UserDetails.entity.UserDetails;
import com.tech.UserDetails.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/userDetails")
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;


    @GetMapping("/getUser")
    public List<UserDetails> getAllUserDetails() {
        return userDetailsService.getAllUserDetails();
    }

    @GetMapping("getById/{id}")
    public Optional<UserDetails> getUserDetails(@PathVariable int id) {
        return userDetailsService.getUserDetailsById(id);
    }

    @PostMapping("/addUserDetails")
    public UserDetails createUserDetails(@RequestBody UserDetails userDetails) {
        return userDetailsService.createUserDetails(userDetails);
    }

    @PutMapping("UpdateById/{id}")
    public UserDetails updateUserDetails(@PathVariable int id, @RequestBody UserDetails userDetails) {
        return userDetailsService.updateUserDetails(id, userDetails);
    }

    @DeleteMapping("deleteById/{id}")
    public boolean deleteUserDetails(@PathVariable int id) {
        return userDetailsService.deleteUserDetails(id);
    }
}
