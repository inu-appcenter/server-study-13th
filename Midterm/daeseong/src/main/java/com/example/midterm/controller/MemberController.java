package com.example.midterm.controller;

import com.example.midterm.domain.Member;
import com.example.midterm.model.member.MemberPatchRequest;
import com.example.midterm.model.member.MemberResponse;
import com.example.midterm.model.member.MemberSaveRequest;
import com.example.midterm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Void> saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity<Void> patchMember(@PathVariable Long memberId, @RequestBody @Valid MemberPatchRequest memberPatchRequest) {
        memberService.patchMember(memberId, memberPatchRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("members/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
    @GetMapping("members/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId) {
        Member member = memberService.findOneMember(memberId);

        return new MemberResponse(member);
    }
}
