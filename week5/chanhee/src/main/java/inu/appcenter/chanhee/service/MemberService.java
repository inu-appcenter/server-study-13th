package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.model.member.MemberLoginRequest;
import inu.appcenter.chanhee.model.member.MemberResponse;
import inu.appcenter.chanhee.model.member.MemberSaveRequest;
import inu.appcenter.chanhee.model.member.MemberUpdateRequest;
import inu.appcenter.chanhee.repository.MemberRepository;
import inu.appcenter.chanhee.repository.query.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final PasswordEncoder passwordEncoder;

    // 관리자 권한 추가
    @PostConstruct
    public void saveAdminMember() {
        Member adminMember = Member.AdminMember("admin", passwordEncoder.encode("0000"), "관리자");
        memberRepository.save(adminMember);
    }

    // 회원 등록
    @Transactional
    public Long saveMember(MemberSaveRequest memberSaveRequest) {

        // 회원 이메일 중복 검사
        if(memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()) {
            throw new MemberException("이미 가입된 회원입니다.");
        }

        // 이미 가입된 회원이 아니라면
        Member findMember = Member.createMember(memberSaveRequest.getEmail(),
                passwordEncoder.encode(memberSaveRequest.getPassword()),
                        memberSaveRequest.getName());

        Member savedMember = memberRepository.save(findMember);
        return savedMember.getId();
    }


    // @Transactional을 달아줘야 할까?
    public Member loginMember(MemberLoginRequest memberLoginRequest) {

        // 회원의 이메일이 존재하는지 확인
        Member findMember = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new MemberException("존재하지 않는 이메일입니다."));

        // 로그인 요청 비밀번호와 DB의 암호화된 비밀번호가 일치하는지 확인
        if(!passwordEncoder.matches(memberLoginRequest.getPassword(), findMember.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        // 비밀번호가 일치한다면
        return findMember;
    }

    // 회원 수정
    @Transactional
    public void updateMember(Long id, MemberUpdateRequest memberUpdateRequest) {

        // 해당 회원의 id 찾기
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        findMember.updateMember(memberUpdateRequest.getName(),
                passwordEncoder.encode(memberUpdateRequest.getPassword()));
    }

    // 회원 삭제
    @Transactional
    public void deleteMember(Long id) {

        // 해당 회원의 id 존재 여부 확인
        Member findMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        findMember.deleteMember(findMember.getStatus());
    }

    // 회원 목록 조회
    public List<Member> findAll() {

        List<Member> members = memberQueryRepository.findAllWithOrderList();

        return members;
    }

    // 회원 ID 조회 = 회원 상세 조회
    public Member findMemberWithOrderById(Long memberId) {

        Member member = memberQueryRepository.findMemberWithOrderById(memberId);

        return member;
    }
}
