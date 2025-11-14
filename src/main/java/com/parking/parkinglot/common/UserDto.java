package com.parking.parkinglot.common;

public class UserDto {
    private Long id;
    private String email;
    private String username;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public UserDto(Long id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }
}
