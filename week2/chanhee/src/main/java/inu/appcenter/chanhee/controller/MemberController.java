package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.Member;
import inu.appcenter.chanhee.model.member.MemberByTodoResponse;
import inu.appcenter.chanhee.model.member.MemberResponse;
import inu.appcenter.chanhee.model.member.MemberSaveRequest;
import inu.appcenter.chanhee.model.member.MemberUpdateRequest;
import inu.appcenter.chanhee.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    // 회원 저장
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody MemberSaveRequest memberSaveRequest) {
        memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 회원 수정
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId,
                                       @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(memberId, memberUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 리스트 조회
    @GetMapping("/members")
    public List<MemberResponse> findAllMember() {
        List<Member> members = memberService.findAll();
        List<MemberResponse> memberResponseList = members.stream()
                .map(member -> new MemberResponse(member))
                .collect(Collectors.toList());

        return memberResponseList;
    }

    // 회원 단건 조회 -> 회원의 todo 리스트까지 조회 (1:N)
    @GetMapping("members/{memberId}")
    public MemberByTodoResponse findMemberById(@PathVariable Long memberId) {
        Member member = memberService.findById(memberId);

        return new MemberByTodoResponse(member);
    }
}
