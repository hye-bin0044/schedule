package com.schedule.schedule.dto.response;

import lombok.Getter;

@Getter
public class ScheduleWithUsernameResponseDto {

    private final String task;

    private final String place;

    private final String username;

    public ScheduleWithUsernameResponseDto(String task, String place, String username) {
        this.task = task;
        this.place = place;
        this.username = username;
    }
}
