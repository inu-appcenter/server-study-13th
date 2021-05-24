package com.example.midterm.domain;

import com.example.midterm.domain.status.MemberStatus;
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
    private String password;
    private int age;
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    public static Member createMember(String email, String password, int age, String name) {
        Member member = new Member();

        member.email = email;
        member.password = password;
        member.age = age;
        member.name = name;
        member.status = MemberStatus.ACTIVATED;

        return member;
    }

    public void patch(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void deleteMember() {
        this.status = MemberStatus.DEACTIVATED;
    }
}
