package com.example.hw2.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")

    private Long id;
    private String email;
    private String name;
    private int age;

    @OneToMany(mappedBy = "member")
    private List<ToDo> todoList = new ArrayList<>();

    public static Member createMember(String email, String name, int age) {
        Member member = new Member();
        member.email = email;
        member.name = name;
        member.age = age;

        return member;
    }

    public void change(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }
}
