package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.model.member.MemberResponse;
import inu.appcenter.chanhee.model.member.MemberSaveRequest;
import inu.appcenter.chanhee.model.member.MemberUpdateRequest;
import inu.appcenter.chanhee.service.MemberService;
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

    // 회원 가입
    @PostMapping("/members")
    public ResponseEntity saveMember(@Valid @RequestBody MemberSaveRequest memberSaveRequest) {

        Long memberId = memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    // 회원 수정
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId,
                                       @RequestBody MemberUpdateRequest memberUpdateRequest) {

        memberService.updateMember(memberId, memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 탈퇴
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {

        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 목록 조회 -> 회원리스트 + 회원의 주문리스트
    @GetMapping("/members")
    public List<MemberResponse> findAllMember() {
        List<Member> members = memberService.findAll();
        List<MemberResponse> memberResponseList = members.stream()
                .map(member -> new MemberResponse(member))
                .collect(Collectors.toList());

        return memberResponseList;
    }

    // 회원 ID 조회  -> 회원 + 회원의 주문리스트
    @GetMapping("/members/{memberId}")
    public MemberResponse findMemberById(@PathVariable Long memberId) {
        Member member = memberService.findMemberById(memberId);

        return new MemberResponse(member);
    }
}
