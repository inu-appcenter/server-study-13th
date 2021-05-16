package inu.appcenter.chanhee.domain.member;

import inu.appcenter.chanhee.domain.BaseEntity;
import inu.appcenter.chanhee.domain.order.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

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

    // 회원 가입
    public static Member createMember(String email, String password, String name) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVE;
        return member;
    }

    // 회원 정보 수정
    public void updateMember(String name) {
        this.name = name;
    }

    // 회원 탈퇴
    public void deleteMember(MemberStatus status) {
        this.status = status.DELETE;
    }
}
