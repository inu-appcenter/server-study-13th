package inu.appcenter.study3_1.domain;

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

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    public static Member createMember(String email, String password, String name){
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVE;
        return member;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void delete() {
        this.status = MemberStatus.DELETED;
    }
}
