package com.example.hw3.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)  // 꼭 String으로
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private final List<Order> orderList = new ArrayList<>();

    public static Member createMember(String email, String password, String name) {
        Member member = new Member();

        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVE;

        return member;
    }

    public void patch(String name) {
        this.name = name;
    }
}
