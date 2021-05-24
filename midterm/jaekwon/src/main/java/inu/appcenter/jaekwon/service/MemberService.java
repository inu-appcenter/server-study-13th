package inu.appcenter.jaekwon.service;

import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.exception.MemberException;
import inu.appcenter.jaekwon.model.member.MemberSaveRequest;
import inu.appcenter.jaekwon.model.member.MemberUpdateRequest;
import inu.appcenter.jaekwon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {
        if(memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()){
            throw new MemberException("이미 가입된 메일이 존재합니다.");
        }
        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getPassword(),
                memberSaveRequest.getName(), memberSaveRequest.getAge());
        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("없는 회원힙니다."));
        member.updateMember(memberUpdateRequest.getAge(), memberUpdateRequest.getName());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("회원의 게시글이 없습니다."));
        member.deleteMember();
    }

    public Member findWithPostById(Long memberId) {
        Member findMember = memberRepository.findWithPostsById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 멤버입니다."));
        return findMember;
    }
}
