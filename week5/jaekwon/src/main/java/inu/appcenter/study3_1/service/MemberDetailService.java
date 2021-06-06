package inu.appcenter.study3_1.service;

import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.repository.MemberRepository;
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
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberPk) throws UsernameNotFoundException {
        Member findMember = memberRepository.findWithRolesById(Long.parseLong(memberPk))
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        List<SimpleGrantedAuthority> authorities = findMember.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

        return new User(Long.toString(findMember.getId()), "", authorities);
    }
}
