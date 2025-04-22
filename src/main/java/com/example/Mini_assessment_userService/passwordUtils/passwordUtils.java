package com.example.Mini_assessment_userService.passwordUtils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class passwordUtils {
private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

public String hashPassword(String PlainPassword){
    return encoder.encode(PlainPassword);
}

}
