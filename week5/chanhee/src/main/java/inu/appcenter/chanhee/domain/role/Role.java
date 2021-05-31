package inu.appcenter.chanhee.domain.role;

import inu.appcenter.chanhee.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 권한 생성
    public static Role createRole(Member member) {
        Role role = new Role();
        role.roleName = "ROLE_MEMBER";
        role.member = member;
        return role;
    }

    // 관리자 권한 추가
    public static Role createAdminMember(Member member) {
        Role role = new Role();
        role.roleName = "ROLE_ADMIN";       // 왜 ROLE_ADMIN 형식이였지?
        role.member = member;
        return role;
    }
}
