package inu.appcenter.yunah.domain;

import inu.appcenter.yunah.domain.status.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    // 회원(member) : 게시글(post) = 1 : N
    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    // 회원(member) : 댓글(comment) = 1 : N
    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    public static Member createMember(String email, String password, String name, int age) {

        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.age = age;
        member.status = MemberStatus.ACTIVE;
        return member;
    }

    public void updateMember(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public void deleteMember(Member member) {
        this.status = MemberStatus.DELETED;
    }
}
