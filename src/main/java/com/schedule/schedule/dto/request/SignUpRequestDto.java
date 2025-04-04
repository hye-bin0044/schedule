package com.schedule.schedule.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequestDto {

    private final String username;

    private final String email;

    private final String password;

    public SignUpRequestDto(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
