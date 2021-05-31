package inu.appcenter.study3_1.service;

import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.exception.MemberException;
import inu.appcenter.study3_1.model.member.MemberLoginRequest;
import inu.appcenter.study3_1.model.member.MemberResponseWithOrderAndProduct;
import inu.appcenter.study3_1.model.member.MemberSaveRequest;
import inu.appcenter.study3_1.model.member.MemberUpdateRequest;
import inu.appcenter.study3_1.repository.MemberRepository;
import inu.appcenter.study3_1.repository.query.MemberQueryRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final MemberQueryRepository memberQueryRepository;

//    @PostConstruct
//    public void saveAdminMember(){
//        Member adminMember = Member.createAdminMember("admin", passwordEncoder.encode("admin"), "운영자");
//        memberRepository.save(adminMember);
//    }

    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest){
        if(memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()){
            throw new MemberException("이미 가입된 이메일 존재합니다.");
        }
        Member member = Member.createMember(memberSaveRequest.getEmail(),
                                            passwordEncoder.encode(memberSaveRequest.getPassword()),
                                            memberSaveRequest.getName());
        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        member.changeName(memberUpdateRequest.getName(),
                            passwordEncoder.encode(memberUpdateRequest.getPassword()));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        member.delete();
    }

    public List<MemberResponseWithOrderAndProduct> findAll() {
        List<Member> findAllMember = memberQueryRepository.findWithOrderAndProductAllMember();
        List<MemberResponseWithOrderAndProduct> result = findAllMember.stream().map(member -> new MemberResponseWithOrderAndProduct(member))
                .collect(Collectors.toList());
        return result;
    }

    public Member findById(Long memberId) {
        Member member = memberRepository.findWithOrderListById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        return member;
    }

    public Member loginMember(MemberLoginRequest memberLoginRequest) {
        Member findMember = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 이메일입니다."));
        if(!passwordEncoder.matches(memberLoginRequest.getPassword(), findMember.getPassword())){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return findMember;
    }


}
