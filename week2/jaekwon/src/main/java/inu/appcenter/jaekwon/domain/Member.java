package inu.appcenter.jaekwon.domain;


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

    public void change(int age, String name){
        this.age = age;
        this.name = name;
    }
}
