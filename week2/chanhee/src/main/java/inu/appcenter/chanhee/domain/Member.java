package inu.appcenter.chanhee.domain;

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

    // 팀 - 회원에서 팀과 같은 역할
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")

    private Long id;

    private String email;

    private int age;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Todo> todoList = new ArrayList<>();

    public static Member createMember(String email, int age, String name) {
        Member member = new Member();
        member.email = email;
        member.age = age;
        member.name = name;
        return member;
    }

    public void changeInfo(int age, String name) {
        this.age = age;
        this.name = name;
    }
}
