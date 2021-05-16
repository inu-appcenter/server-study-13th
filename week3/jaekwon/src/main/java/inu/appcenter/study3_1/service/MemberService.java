package inu.appcenter.study3_1.service;

import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.exception.MemberException;
import inu.appcenter.study3_1.model.member.MemberSaveRequest;
import inu.appcenter.study3_1.model.member.MemberUpdateRequest;
import inu.appcenter.study3_1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long saveMember(MemberSaveRequest memberSaveRequest){
        if(memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()){
            throw new MemberException("이미 가입된 이메일 존재합니다.");
        }

        Member member = Member.createMember(memberSaveRequest.getEmail(),
                                            memberSaveRequest.getPassword(),
                                            memberSaveRequest.getName());
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        member.changeName(memberUpdateRequest.getName());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        member.delete();
    }

    public List<Member> findAll() {
        List<Member> findWithOrderAllMember = memberRepository.findWithOrderListAll();
        return findWithOrderAllMember;
    }

    public Member findById(Long memberId) {
        Member member = memberRepository.findWithOrderListById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        return member;
    }
}
