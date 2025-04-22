package com.example.Mini_assessment_userService.userService;

import com.example.Mini_assessment_userService.dataClass.User;
import com.example.Mini_assessment_userService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {


    @Autowired
    private UserRepo userRepo;

    public Optional<User> findByEmail(String email){
        return userRepo.findByEmail(email);
    }
    public Optional<User>findById(String id){
        return userRepo.findById(id);
    }
    public void save(User user){
        userRepo.save(user);
    }
}
