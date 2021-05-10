package com.example.hw2.service;

import com.example.hw2.domain.Member;
import com.example.hw2.model.member.request.MemberSaveRequest;
import com.example.hw2.model.member.request.MemberUpdateRequest;
import com.example.hw2.repository.MemberRepository;
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

    @Transactional
    public void saveMember(MemberSaveRequest memberSaveRequest) {
        validatedDuplicatedName(memberSaveRequest.getName());

        Member member = Member.createMember(memberSaveRequest.getEmail(), memberSaveRequest.getName(), memberSaveRequest.getAge());
        memberRepository.save(member);
    }

    @Transactional  // forgot this......
    public void patchMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(IllegalStateException::new);

        validatedDuplicatedName(memberUpdateRequest.getName());

        member.change(memberUpdateRequest.getEmail(), memberUpdateRequest.getName(), memberUpdateRequest.getAge());
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(IllegalStateException::new);
    }

    private void validatedDuplicatedName(String name) {
        Optional<Member> result = memberRepository.findByName(name);

        if (result.isPresent()) {
            throw new IllegalStateException();
        }
    }
}
