package com.schedule.schedule.repository;

import com.schedule.schedule.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//member의 id가 Long 타입이라 선언
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);

    default Member findMemberByUsernameOrElseThrow(String username) {
        return findMemberByUsername(username)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "해당 유저가 존재하지 않습니다. : " + username)
                );
    }

    default Member findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "해당 아이디가 존재하지 않습니다. : " + id)
                );
    };


}



