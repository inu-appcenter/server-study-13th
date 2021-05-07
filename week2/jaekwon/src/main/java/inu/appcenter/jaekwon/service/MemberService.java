package inu.appcenter.jaekwon.service;

import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.exception.MemberException;
import inu.appcenter.jaekwon.model.member.MemberSaveRequest;
import inu.appcenter.jaekwon.model.member.MemberUpdateRequest;
import inu.appcenter.jaekwon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    //  회원 저장
    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {
        //  중복체크
        Optional<Member> result = memberRepository.findByEmail(memberSaveRequest.getEmail());
        if(result.isPresent()){
            throw new MemberException("이메일이 중복되었습니다.");
        }

        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getAge(), memberSaveRequest.getName());
        memberRepository.save(member);
    }

    //  회원 수정
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        member.change(memberUpdateRequest.getAge(), memberUpdateRequest.getName());
    }

    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();

        return members;
    }

    public Member findById(Long memberId) {
        Member member = memberRepository.findWithTodoById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원id이거나 회원의 할일이 없습니다."));

        return member;
    }
}
