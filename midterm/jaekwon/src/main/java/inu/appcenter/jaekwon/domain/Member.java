package inu.appcenter.jaekwon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;

    private String password;

    private  int age;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    public static Member createMember(String email, String password, String name, Integer age) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.age = age;
        member.status = MemberStatus.ACTIVE;
        return member;
    }

    public void updateMember(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void deleteMember() {
        this.status = MemberStatus.DELETED;
    }
}
