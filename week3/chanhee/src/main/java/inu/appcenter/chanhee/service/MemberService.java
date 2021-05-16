package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.model.member.MemberSaveRequest;
import inu.appcenter.chanhee.model.member.MemberUpdateRequest;
import inu.appcenter.chanhee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 등록
    @Transactional
    public Long saveMember(MemberSaveRequest memberSaveRequest) {

        // 이메일 중복 체크
        if(memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()) {
            throw new MemberException("이미 가입된 이메일입니다.");
        }

        // 이메일 중복 체크 통과 하면 회원 생성
        Member member = Member.createMember(memberSaveRequest.getEmail(),
                                            memberSaveRequest.getPassword(),
                                            memberSaveRequest.getName());

        // 회원 등록
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    // 회원 수정
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {

        // 회원 id 존재여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원 id가 존재한다면
        member.updateMember(memberUpdateRequest.getName());
    }

    // 회원 탈퇴
    @Transactional
    public void deleteMember(Long memberId) {

        // 회원 id 존재여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원 id가 존재한다면
        member.deleteMember(member.getStatus());
    }

    // 회원 리스트 조회
    public List<Member> findAll() {
        List<Member> members = memberRepository.findWithOrderListAll();
        return members;
    }

    // 회원 id 조회
    public Member findMemberById(Long memberId) {

        // 회원 id 존재여부 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원 id가 존재한다면
        Member member = memberRepository.findWithOrderListById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 주문입니다."));

        return member;
    }
}
