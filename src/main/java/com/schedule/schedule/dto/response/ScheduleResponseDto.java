package com.schedule.schedule.dto.response;

import com.schedule.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {

    private final Long id;

    private final String task;

    private final String place;

    public ScheduleResponseDto(Long id, String task, String place) {
        this.id = id;
        this.task = task;
        this.place = place;
    }
    // 어떤 회원이 작성했는지 요청 정보가 있어야 함.

    // toDto를 통해 Entity를 Dto로 변환
    // 매개변수로 전달된 schedule이 ScheduleResponseDto로 변환되어 전달
    public static ScheduleResponseDto toDto(Schedule schedule){
        return new ScheduleResponseDto(schedule.getId(), schedule.getTask(), schedule.getPlace());
    }
}
