package com.example.Mini_assessment_userService.userService;

import com.example.Mini_assessment_userService.dataClass.ApiResponse;
import com.example.Mini_assessment_userService.dataClass.User;
import com.example.Mini_assessment_userService.repo.UserRepo;
import com.example.Mini_assessment_userService.utils.jwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private jwtUtils JwtUtils;

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public void save(User user){
        userRepo.save(user);
    }

    public ResponseEntity<?> check(String tokenHeader) {
        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok(new ApiResponse<>(false,"Missing token",null));
        }
        String token = tokenHeader.substring(7);
        if (JwtUtils.validateToken(token)) {
            String email = JwtUtils.extractEmail(token);
            Optional<User> currentUserDetails = findByEmail(email);

            return ResponseEntity.ok(new ApiResponse<>(true,"Profile loaded",currentUserDetails));
        }
         return ResponseEntity.ok(new ApiResponse<>(false,"token is null or invalidate",null));
    }
}
