package com.example.hw2.controller;

import com.example.hw2.domain.Member;
import com.example.hw2.model.member.request.MemberSaveRequest;
import com.example.hw2.model.member.request.MemberUpdateRequest;
import com.example.hw2.model.member.response.MemberResponse;
import com.example.hw2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody MemberSaveRequest memberSaveRequest){
        memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity patchMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.patchMember(memberId, memberUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/members")
    public List<MemberResponse> findAllMembers(){
        List<Member> members = memberService.findAll();
        List<MemberResponse> memberResponses = members.stream().map(MemberResponse::new).collect(Collectors.toList());

        return memberResponses;
    }

    @GetMapping("/members/{memberId}")
    public MemberResponse findMemberByMemberId(@PathVariable Long memberId){
        Member member = memberService.findById(memberId);

        return new MemberResponse(member);
    }
}
