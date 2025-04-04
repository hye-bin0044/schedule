package com.schedule.schedule.service;

import com.schedule.schedule.dto.response.MemberResponseDto;
import com.schedule.schedule.dto.response.SignUpResponseDto;
import com.schedule.schedule.entity.Member;
import com.schedule.schedule.exception.PasswordMismatchException;
import com.schedule.schedule.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 생성
    public SignUpResponseDto signUp(String userName, String email, String password) {

//        Member member = new Member(userName, password, email); 입력 받을때, Member 엔티티의 순서와 맞아야함!
        Member member = new Member(userName, email, password);

        // 저장한 멤버를 반환할 수 있음
        Member savedMember = memberRepository.save(member);

        return new SignUpResponseDto(savedMember.getId(), savedMember.getUsername(), savedMember.getEmail());
    }

    // 단건 조회
    public MemberResponseDto findById(Long id) {

        Optional<Member> optionalMember = memberRepository.findById(id);

        if(optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디가 존재하지 않습니다 : " + id);
        }

        Member findMember = optionalMember.get();

        return new MemberResponseDto(findMember.getUsername(), findMember.getEmail());
    }

    // 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword){

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        // 비밀번호 일치하는지 검증
        if (!findMember.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findMember.updatePassword(newPassword);
    }

    // 회원 삭제
    @Transactional
    public void delete(Long id, String password) {
        Member member = memberRepository.findByIdOrElseThrow(id);

        if (!password.equals(member.getPassword())) {
            throw new PasswordMismatchException();
        }
        memberRepository.deleteById(id);
    }
}
