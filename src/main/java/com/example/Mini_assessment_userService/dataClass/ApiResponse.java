package com.example.Mini_assessment_userService.dataClass;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private Boolean success;
    private String message;
    private T data;

    public ApiResponse(Boolean success, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
