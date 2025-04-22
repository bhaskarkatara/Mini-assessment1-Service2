package com.example.Mini_assessment_userService.repo;

import com.example.Mini_assessment_userService.dataClass.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepo extends MongoRepository<User,String> {
  Optional<User>findByEmail(String email);
}
