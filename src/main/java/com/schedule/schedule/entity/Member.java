package com.schedule.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "userMember")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public Member() {
    }

    public Member(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // 비밀번호 업데이트
    public void updatePassword(String password) {
        this.password = password;
    }
}
