package com.example.hw5.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    public static Member createMember(String email, String password, String name) {

        Member member = new Member();

        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVATED;
        member.roles.add(Role.createRole(member));

        return member;
    }

    public static Member createADMIN(String email, String password, String name) {

        Member member = new Member();

        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVATED;
        member.roles.add(Role.createADMIN(member));

        return member;
    }

    public void patch(String password, String name) {

        this.password = password;
        this.name = name;
    }

    public void delete() {

        this.status = MemberStatus.DELETED;
    }
}
