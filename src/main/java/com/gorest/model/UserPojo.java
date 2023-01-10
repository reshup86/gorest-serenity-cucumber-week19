package com.gorest.model;

public class UserPojo {
    private String  name;
    private String email;
    private String gender;
    private String status;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static UserPojo getUserPojo(String name, String gender, String email, String status, String token){
        UserPojo usersPojo = new UserPojo();
        usersPojo.setName(name);
        usersPojo.setEmail(email);
        usersPojo.setGender(gender);
        usersPojo.setStatus(status);
        usersPojo.setToken(token);
        return usersPojo;
    }
}
