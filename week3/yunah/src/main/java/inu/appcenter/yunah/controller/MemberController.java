package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.model.member.MemberResponse;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /*
    회원 가입
     */
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {

        memberService.saveMember(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    회원 수정(이름 변경 가능)
     */
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId, @RequestBody @Valid MemberUpdateRequest memberUpdateRequest) {

        memberService.updateMember(memberId, memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    회원 탈퇴
     */
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {

        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    회원 목록 조회 ( 주문 목록도 같이 조회 )
     */
    @GetMapping("/members")
    public List<MemberResponse> findAllMember() {

        List<MemberResponse> members = memberService.findAll();

        return members;

//        List<Member> members = memberService.findAll();
//        List<MemberResponse> memberResponseList = members.stream()
//                .map(member -> new MemberResponse(member))
//                .collect(Collectors.toList());
    }

    /*
    회원 ID 조회 ( 회원의 주문 목록까지 같이 조회 )
     */
    @GetMapping("/members/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId) {

        Member member = memberService.findById(memberId);

        return new MemberResponse(member);
    }
}
