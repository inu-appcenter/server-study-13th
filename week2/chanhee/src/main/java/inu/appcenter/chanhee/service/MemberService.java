package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.Member;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.model.member.MemberSaveRequest;
import inu.appcenter.chanhee.model.member.MemberUpdateRequest;
import inu.appcenter.chanhee.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원을 만들 때 중복된 이메일이 있으면 안된다.
    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {
        // 이메일 중복 체크
        Optional<Member> result = memberRepository.findByEmail(memberSaveRequest.getEmail());
        if (result.isPresent()) {
            throw new MemberException("이메일이 중복되었습니다.");
        }

        // 이메일 중복 체크를 통과했다면 회원 저장
        Member member = Member.createMember(memberSaveRequest.getEmail(),
                                            memberSaveRequest.getAge(), memberSaveRequest.getName());
        memberRepository.save(member);
    }

    // 회원 정보 수정은 이름과 나이만 변경 가능
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원 id입니다."));

        member.changeInfo(memberUpdateRequest.getAge(), memberUpdateRequest.getName());
    }

    // 회원 리스트 조회
    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    // 회원 단건 조회 -> 회원의 todo 리스트까지 조회
    public Member findById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원 id 입니다."));

        return member;
    }
}
