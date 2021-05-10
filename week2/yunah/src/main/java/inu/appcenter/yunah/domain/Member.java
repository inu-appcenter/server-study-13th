package inu.appcenter.yunah.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{                    // "팀" - 회원 / "1" : N

    @Id    // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private int age;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Todo> todoList = new ArrayList<>();

    public static Member createMember(String email, String name, int age) {

        Member member = new Member();
        member.email = email;
        member.name = name;
        member.age = age;
        return member;
    }

    public void changeMember(int age, String name) {

        this.age = age;
        this.name = name;
    }
}
