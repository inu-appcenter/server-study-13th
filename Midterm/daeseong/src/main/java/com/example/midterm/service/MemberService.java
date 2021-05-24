package com.example.midterm.service;

import com.example.midterm.advice.exception.MemberException;
import com.example.midterm.domain.Member;
import com.example.midterm.model.member.MemberPatchRequest;
import com.example.midterm.model.member.MemberSaveRequest;
import com.example.midterm.repository.MemberRepository;
import com.example.midterm.repository.query.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;

    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {
        checkAlreadyExists(memberSaveRequest.getEmail());

        Member savedMember = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getPassword(), memberSaveRequest.getAge(), memberSaveRequest.getName());

        memberRepository.save(savedMember);
    }

    /**
     * No duplicate checking, because of no email change.
     */
    @Transactional
    public void patchMember(Long memberId, MemberPatchRequest memberPatchRequest) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("No Member Exists"));
        
        findMember.patch(memberPatchRequest.getAge(), memberPatchRequest.getName());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("No Member Exists"));

        findMember.deleteMember();
    }

    /**
     * @param memberId want to find
     * @return found Member
     */
    public Member findOneMember(Long memberId) {
        return memberQueryRepository.findMemberWithPostsByMemberId(memberId);
    }

    /**
     *  If email presents in memberRepository,
     *  throw MemberException
     */
    private void checkAlreadyExists(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberException("Already Exists");
        }
    }
}
