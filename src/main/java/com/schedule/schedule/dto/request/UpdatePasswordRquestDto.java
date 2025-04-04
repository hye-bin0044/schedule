package com.schedule.schedule.dto.request;

import lombok.Getter;

@Getter
public class UpdatePasswordRquestDto {

    private final String oldPassword;

    private final String newPassword;

    public UpdatePasswordRquestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
