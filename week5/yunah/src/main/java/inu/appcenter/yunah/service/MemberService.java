package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.model.member.MemberLoginRequest;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.repository.MemberRepository;
import inu.appcenter.yunah.repository.query.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberQueryRepository memberQueryRepository;

    @PostConstruct
    public void saveAdminMember() {
        Member adminMember = Member.createAdminMember("admin@naver.com", passwordEncoder.encode("admin"), "admin");
        memberRepository.save(adminMember);
    }

    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {

        if (memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()) {
            throw new MemberException("이미 가입된 이메일이 존재합니다.");
        }

        Member member = Member.createMember(memberSaveRequest.getEmail(),
                passwordEncoder.encode(memberSaveRequest.getPassword()),
                memberSaveRequest.getName());        // password는 꼭 passwordEncoder를 통해 암호화해주어야 한다.
        memberRepository.save(member);
    }

    public Member loginMember(MemberLoginRequest memberLoginRequest) {

        Member findMember = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        if (!passwordEncoder.matches(memberLoginRequest.getPassword(), findMember.getPassword())) {
            throw new MemberException("비밀번호가 일치하지 않습니다.");
        }
        return findMember;
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        member.updateMember(memberUpdateRequest.getName(), passwordEncoder.encode(memberUpdateRequest.getPassword()));
    }

    @Transactional
    public void deleteMember(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        member.deleteMember(member);
    }

    public List<Member> findAllMember() {
        List<Member> members = memberQueryRepository.findMemberWithOrderById();
        return members;
    }

    public Member findMember(Long memberId) {
        Member member = memberQueryRepository.findMemberById(memberId);
        return member;
    }
}
