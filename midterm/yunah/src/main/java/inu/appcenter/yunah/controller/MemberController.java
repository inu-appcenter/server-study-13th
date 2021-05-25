package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.model.member.MemberWithPostResponse;
import inu.appcenter.yunah.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

        memberService.save(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    회원 수정 ( 이름, 나이 변경 )
     */
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId, @RequestBody @Valid MemberUpdateRequest memberUpdateRequest) {

        memberService.update(memberId, memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    회원 탈퇴 ( 상태 변경 )
     */
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {

        memberService.delete(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    회원 ID 조회 ( 삭제 상태가 아닌 회원의 삭제 상태가 아닌 게시글 목록까지 조회 )
     */
    @GetMapping("/members/{memberId}")
    public MemberWithPostResponse findMemberById(@PathVariable Long memberId) {

        Member memberWithPostById = memberService.findMemberWithPostById(memberId);
        MemberWithPostResponse response = new MemberWithPostResponse(memberWithPostById);
        return response;
    }
}
