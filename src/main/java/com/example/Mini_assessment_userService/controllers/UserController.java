package com.example.Mini_assessment_userService.controllers;

import com.example.Mini_assessment_userService.dataClass.User;
import com.example.Mini_assessment_userService.dataClass.userUpdateDetails;
import com.example.Mini_assessment_userService.passwordUtils.passwordUtils;
import com.example.Mini_assessment_userService.userService.UserService;
import com.example.Mini_assessment_userService.utils.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private passwordUtils Passwordutils;

    @Autowired
    private jwtUtils JwtUtils;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String tokenHeader) {
        return userService.check(tokenHeader);
    }

    @PutMapping("/update")
    public ResponseEntity<?> userProfileUpdate(@RequestHeader("Authorization") String tokenHeader, @RequestBody userUpdateDetails updatedUser) {

        ResponseEntity<?> checkResponse = userService.check(tokenHeader);
        HttpStatus status = (HttpStatus) checkResponse.getStatusCode();

        if (status == HttpStatus.BAD_REQUEST || status == HttpStatus.UNAUTHORIZED) {
            return checkResponse;
        }

        @SuppressWarnings("unchecked")
        Optional<User> optionalUser = (Optional<User>) checkResponse.getBody();


        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User existingUser = optionalUser.get();
        existingUser.setName(updatedUser.getName());

        // Hash password before saving
        String hashPassword = Passwordutils.hashPassword(updatedUser.getPassword());
        existingUser.setPassword(hashPassword);

        userService.save(existingUser);

        return ResponseEntity.ok(existingUser);
    }

}


