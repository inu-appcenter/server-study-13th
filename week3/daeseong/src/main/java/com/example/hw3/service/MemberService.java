package com.example.hw3.service;

import com.example.hw3.domain.Member;
import com.example.hw3.exception.MemberException;
import com.example.hw3.model.member.MemberPatchRequest;
import com.example.hw3.model.member.MemberSaveRequest;
import com.example.hw3.repository.MemberRepository;
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
    public Long saveMember(MemberSaveRequest memberSaveRequest) {
        if(memberRepository.findByEmail(memberSaveRequest.getEmail()).isPresent()){
            throw new MemberException("Already Exists");
        }

        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getPassword(), memberSaveRequest.getName());

        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    @Transactional
    public void patchMember(MemberPatchRequest memberPatchRequest, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("No Member"));

        member.patch(memberPatchRequest.getName());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public List<Member> findAll() {

        return memberRepository.findWithOrderListAll();
    }

    public Member findMember(Long memberId) {

        return memberRepository.findWithOrderListById(memberId)
                .orElseThrow(()-> new MemberException("No Member"));
    }
}
