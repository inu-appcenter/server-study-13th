package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.status.MemberStatus;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.repository.MemberRepository;
import inu.appcenter.yunah.repository.query.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public void save(MemberSaveRequest memberSaveRequest) {

        if (memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent())
            throw new MemberException("이미 가입된 이메일이 존재합니다.");

        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getPassword(), memberSaveRequest.getName(), memberSaveRequest.getAge());
        memberRepository.save(member);
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequest memberUpdateRequest) {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        findMember.updateMember(memberUpdateRequest.getName(), memberUpdateRequest.getAge());
    }

    @Transactional
    public void delete(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        member.deleteMember(member);
    }

    public Member findMemberWithPostById(Long memberId) {

        Member memberById = memberQueryRepository.findMemberById(memberId);

        return memberById;
    }
}

