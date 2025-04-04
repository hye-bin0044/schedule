package com.schedule.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String task;

    @Column(nullable = false)
    private String place;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Schedule() {
    }

    public Schedule(String task, String place) {
        this.task = task;
        this.place = place;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
