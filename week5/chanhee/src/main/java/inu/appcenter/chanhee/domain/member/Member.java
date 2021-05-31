package inu.appcenter.chanhee.domain.member;

import inu.appcenter.chanhee.domain.BaseEntity;
import inu.appcenter.chanhee.domain.order.Order;
import inu.appcenter.chanhee.domain.role.Role;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    // 회원 등록
    public static Member createMember(String email, String password, String name) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVE;
        member.roles.add(Role.createRole(member));
        return member;
    }

    // 관리자 권한 추가
    public static Member AdminMember(String email, String password, String name) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVE;
        member.roles.add(Role.createAdminMember(member));
        return member;
    }

    // 회원 수정
    public void updateMember(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // 회원 삭제
    public void deleteMember(MemberStatus status) {
        this.status = status.DELETE;
    }
}
