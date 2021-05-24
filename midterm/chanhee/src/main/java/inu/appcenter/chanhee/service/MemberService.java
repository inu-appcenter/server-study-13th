package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.member.MemberStatus;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.model.member.MemberSaveRequest;
import inu.appcenter.chanhee.model.member.MemberUpdateRequest;
import inu.appcenter.chanhee.repository.MemberRepository;
import inu.appcenter.chanhee.repository.query.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    // 회원 가입
    @Transactional
    public Long saveMember(MemberSaveRequest memberSaveRequest) {

        // 이메일 중복 확인
        if (memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()) {
            throw new MemberException("이미 가입된 이메일입니다.");
        }

        // 이메일 중복 확인 통과 후 회원 가입
        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getPassword(),
                memberSaveRequest.getAge(), memberSaveRequest.getName());

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    // 회원 정보 수정
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {

        // 회원이 존재하는지 id 조회
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원이 존재한다면
        findMember.updateMember(memberUpdateRequest.getAge(), memberUpdateRequest.getName());
    }

    // 회원 탈퇴
    @Transactional
    public void deleteMember(Long memberId) {

        // 회원이 존재하는지 id 조회
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원이 존재한다면
        findMember.deleteMember(findMember.getStatus());
    }

    // 회원 Id 조회
    public List<Member> findMemberById(Long memberId) {

        // 회원이 존재하는지 id 조회
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원이 존재한다면
        List<Member> members = memberQueryRepository.findMemberWithPostById(memberId);

        return members;
    }
}
