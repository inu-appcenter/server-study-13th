package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.model.member.MemberResponse;
import inu.appcenter.yunah.model.member.MemberResponseTodo;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.service.MemberService;
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

    /*
    회원 생성
     */
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody MemberSaveRequest memberSaveRequest) {

        memberService.saveMember(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    회원 수정(이름 및 나이)
     */
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId,
                                       @RequestBody MemberUpdateRequest memberUpdateRequest) {

        memberService.updateMember(memberId, memberUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    회원 리스트 조회
    */
    @GetMapping("/members")
    public List<MemberResponse> findAllMember() {

        List<Member> members = memberService.findAll();
        List<MemberResponse> memberResponseList = members.stream()
                .map(member -> new MemberResponse(member))
                .collect(Collectors.toList());

        return memberResponseList;
    }

    /*
    회원 단건 조회
    */
    @GetMapping("/members/{memberId}")
    public MemberResponseTodo findById(@PathVariable Long memberId) {

        Member member = memberService.findById(memberId);

        return new MemberResponseTodo(member);
    }
}
