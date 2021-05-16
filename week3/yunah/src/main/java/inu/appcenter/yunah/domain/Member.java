package inu.appcenter.yunah.domain;

import inu.appcenter.yunah.domain.status.MemberStatus;
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

    private String name;

    private String password;

    @Enumerated(EnumType.STRING) // ORDINAL인 경우, status 추가 시 기존 값이 변경됨 / String으로 설정할 것.
    private MemberStatus status;

    @OneToMany(mappedBy = "member") // 회원(member) : 주문(order) = 1 : N
    private List<Order> orderList = new ArrayList<>();

    public static Member createMember(String email, String name, String password) {

        Member member = new Member();
        member.email = email;
        member.name = name;
        member.password = password;
        member.status = MemberStatus.ACTIVE;
        return member;
    }

    public void changeMember(String name) {

        this.name = name;
    }

    public void deleteMember(Member member) {

        member.status = MemberStatus.DELETED;
    }
}
