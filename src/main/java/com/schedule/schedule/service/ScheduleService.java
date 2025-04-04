package com.schedule.schedule.service;

import com.schedule.schedule.dto.response.ScheduleResponseDto;
import com.schedule.schedule.dto.response.ScheduleWithUsernameResponseDto;
import com.schedule.schedule.entity.Member;
import com.schedule.schedule.entity.Schedule;
import com.schedule.schedule.repository.MemberRepository;
import com.schedule.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String task, String place, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Schedule schedule = new Schedule(task, place);
        schedule.setMember(findMember);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getTask(), schedule.getPlace());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto) // static으로 선언된 toDto를 불러줌
                .toList();
    }

    public ScheduleWithUsernameResponseDto findById(Long id){
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        Member writer = findSchedule.getMember();

        return new ScheduleWithUsernameResponseDto(findSchedule.getTask(), findSchedule.getPlace(), writer.getUsername());
    }

    public void delete(Long id) {

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(findSchedule);
    }


}
