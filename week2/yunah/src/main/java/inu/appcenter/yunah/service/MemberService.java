package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.repository.MemberRepository;
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

    /*
    회원 가입시 이메일 중복체크
     */
    @Transactional // 변경, 삭제할 경우 변경 감지 기능
    public void saveMember(MemberSaveRequest memberSaveRequest) {

        // 중복 체크
        validateDuplicatedMemberEmail(memberSaveRequest.getEmail());

        // 회원 저장
        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getName(), memberSaveRequest.getAge());
        memberRepository.save(member);
    }


    /*
    회원 수정
    */
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {

        //memberId로 회원 찾기
        Member member = memberRepository.findById((memberId))
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 회원 수정
        member.changeMember(memberUpdateRequest.getAge(), memberUpdateRequest.getName());

    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long memberId) {

        //memberId로 회원 찾기
        Member member = memberRepository.findWithTodoById((memberId))
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        return member;
    }

    private void validateDuplicatedMemberEmail(String email) {

        Optional<Member> result = memberRepository.findByEmail((email));
        if (result.isPresent()) {
            throw new MemberException("회원의 이메일이 중복되었습니다.");
        }
    }
}
