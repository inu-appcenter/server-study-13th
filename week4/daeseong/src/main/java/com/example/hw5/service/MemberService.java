package com.example.hw5.service;

import com.example.hw5.domain.Member;
import com.example.hw5.domain.MemberStatus;
import com.example.hw5.exception.MemberException;
import com.example.hw5.model.member.MemberLoginRequest;
import com.example.hw5.model.member.MemberPatchRequest;
import com.example.hw5.model.member.MemberSaveRequest;
import com.example.hw5.repository.MemberRepository;
import com.example.hw5.repository.query.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @PostConstruct  // 몰라서 "김찬희" 학생에게 물어봤습니다.
    public void saveAdminMember() {

        Member ADMIN = Member.createADMIN("ADMIN", passwordEncoder.encode("ADMIN"), "ADMIN");

        memberRepository.save(ADMIN);
    }

    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {

        if (memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()) {  // 이메일 중복 검사
            throw new MemberException("Email Already Exists!");
        }

        Member member = Member.createMember(memberSaveRequest.getEmail(), passwordEncoder.encode(memberSaveRequest.getPassword()), memberSaveRequest.getName());

        memberRepository.save(member);
    }

    public Member loginMember(MemberLoginRequest memberLoginRequest) {

        Member findMember = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new IllegalStateException("No Member Exists"));

        if (!passwordEncoder.matches(memberLoginRequest.getPassword(), findMember.getPassword())) {
            throw new IllegalStateException("Not Match");
        }

        if (findMember.getStatus().equals(MemberStatus.DELETED)) {  // 탈퇴한 회원이라면 로그인 불가능
            throw new MemberException("Deleted Member");
        }

        return findMember;
    }

    @Transactional
    public void patchMember(MemberPatchRequest memberPatchRequest, String userEmail) {

        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new MemberException("No Email Exists!"));

        member.patch(passwordEncoder.encode(memberPatchRequest.getPassword()), memberPatchRequest.getName());
    }

    @Transactional
    public void deleteMember(String userEmail) {

        Member member = memberRepository.findByEmail(userEmail)
                .orElseThrow(() -> new MemberException("No Email Exists!"));

        member.delete();
    }

    public List<Member> findAll(User user) {

        return memberQueryRepository.findAllMembers();
    }

    public Member findMember(Long memberId) {

        Member member = memberQueryRepository.findMemberById(memberId);

        return member;
    }
}
