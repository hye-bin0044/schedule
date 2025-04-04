package com.schedule.schedule.controller;

import com.schedule.schedule.dto.request.CreateScheduleRequestDto;
import com.schedule.schedule.dto.response.ScheduleResponseDto;
import com.schedule.schedule.dto.response.ScheduleWithUsernameResponseDto;
import com.schedule.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(
            @RequestBody CreateScheduleRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto =
                scheduleService.save(
                        requestDto.getTask(),
                        requestDto.getPlace(),
                        requestDto.getUsername()
        );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 일정 전체 조회
    // findAll이기 때문에 paramete가 필요 없음
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(

    ){
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();

        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    // 일정 조회 (특정 유저)
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleWithUsernameResponseDto> findById(@PathVariable Long id) {
        ScheduleWithUsernameResponseDto scheduleWithUsernameResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleWithUsernameResponseDto, HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        scheduleService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
