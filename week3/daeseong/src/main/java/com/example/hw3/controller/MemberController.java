package com.example.hw3.controller;

import com.example.hw3.domain.Member;
import com.example.hw3.model.member.MemberPatchRequest;
import com.example.hw3.model.member.MemberResponse;
import com.example.hw3.model.member.MemberSaveRequest;
import com.example.hw3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        Long memberId = memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity patchMember(@RequestBody @Valid MemberPatchRequest memberPatchRequest, @PathVariable Long memberId) {
        memberService.patchMember(memberPatchRequest, memberId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/members")
    public List<MemberResponse> findAllMembers(){
        List<Member> members = memberService.findAll();

        return members.stream().map(MemberResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/members/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId){
        Member member = memberService.findMember(memberId);

        return new MemberResponse(member);
    }
}
