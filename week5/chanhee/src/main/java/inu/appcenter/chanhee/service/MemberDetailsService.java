package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 전달받은 회원 id를 사용하여 회원 + 회원의 권한을 조회
     */

    @Override
    public UserDetails loadUserByUsername(String memberPk) throws UsernameNotFoundException {

        Member findMember = memberRepository.findWithRolesById(Long.parseLong(memberPk))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        // SimpleGrantedAuthority 타입으로 권한 리스트를 반환해줘야 되기 때문에 회원의 권한들을 모두 반환한다.
        List<SimpleGrantedAuthority> authorities = findMember.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        // UserDetails 타입을 구현하는 User 객체를 반환, User의 username은 회원의 정보가 들어간다.
        return new User(findMember.getId().toString(), "", authorities);
    }
}
