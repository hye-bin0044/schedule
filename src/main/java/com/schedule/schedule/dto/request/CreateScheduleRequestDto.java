package com.schedule.schedule.dto.request;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private final String task;

    private final String place;

    private final String username;

    public CreateScheduleRequestDto(String task, String place, String username) {
        this.task = task;
        this.place = place;
        this.username = username;
    }

    // 어떤 회원이 작성했는지 요청 정보가 있어야 함.
    // cookie, Session, Token을 활용할 수 있다.
}
