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
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    public static Member createMember(String email, String password, String name) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVE;
        member.roles.add(Role.createRole(member));
        return member;
    }

    public static Member createAdminMember(String email, String password, String name) {
        Member member = new Member();
        member.email = email;
        member.password = password;
        member.name = name;
        member.status = MemberStatus.ACTIVE;
        member.roles.add(Role.createAdminRole(member));
        return member;
    }

    public void updateMember(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void deleteMember(Member member) {
        this.status = MemberStatus.DELETE;
    }
}
