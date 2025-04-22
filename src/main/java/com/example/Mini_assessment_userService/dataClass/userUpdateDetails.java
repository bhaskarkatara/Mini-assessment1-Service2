package com.example.Mini_assessment_userService.dataClass;

public class userUpdateDetails {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public userUpdateDetails(String name, String password) {
        this.name = name;
        this.password = password;
    }

    private String name;
    private String password;

}
