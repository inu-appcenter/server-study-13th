package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.exception.OrderException;
import inu.appcenter.yunah.model.member.MemberResponse;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {

        if (memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()) {
            throw new MemberException("이미 가입된 이메일이 존재합니다.");
        }

        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getName(), memberSaveRequest.getPassword());
        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        member.changeMember(memberUpdateRequest.getName());
    }

    @Transactional
    public void deleteMember(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        member.deleteMember(member);
    }

    public List<MemberResponse> findAll() {

        List<MemberResponse> members = memberRepository.findWithOrderAll();

        return members;
    }

//    public List<Member> findAll() {
//        List<Member> members = memberRepository.findWithOrderAll();
//        return members;
//    }

    public Member findById(Long memberId) {

        Member member = memberRepository.findWithOrderById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        return member;
    }
}
