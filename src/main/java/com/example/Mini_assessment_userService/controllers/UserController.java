package com.example.Mini_assessment_userService.controllers;

import com.example.Mini_assessment_userService.dataClass.User;
import com.example.Mini_assessment_userService.dataClass.userUpdateDetails;
import com.example.Mini_assessment_userService.passwordUtils.passwordUtils;
import com.example.Mini_assessment_userService.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@Component
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private passwordUtils Passwordutils;

    @GetMapping("/profile/{email}")
    public ResponseEntity<?> getProfile(@PathVariable String email) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }

    @PutMapping("/update/{userId}")

    public ResponseEntity<?> userProfileUpdate(@PathVariable String userId, @RequestBody userUpdateDetails updatedUser){
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        User existingUser = optionalUser.get();
        existingUser.setName(updatedUser.getName());
        // hash before save password
        String hashPassword = Passwordutils.hashPassword(updatedUser.getPassword());
        existingUser.setPassword(hashPassword);
        userService.save(existingUser);
      return ResponseEntity.ok(existingUser);

    }



}


