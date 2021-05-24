package inu.appcenter.jaekwon.controller;

import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.model.member.MemberResponseWithPosts;
import inu.appcenter.jaekwon.model.member.MemberSaveRequest;
import inu.appcenter.jaekwon.model.member.MemberUpdateRequest;
import inu.appcenter.jaekwon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //  회원 생성
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest){
        memberService.saveMember(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //  회원 수정
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId,
                                       @RequestBody @Valid MemberUpdateRequest memberUpdateRequest){
        memberService.updateMember(memberId, memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  회원 탈퇴
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  회원(단건) + 게시글 조회
    @GetMapping("/members/{memberId}")
    public MemberResponseWithPosts findWithPostById(@PathVariable Long memberId){
        Member findMember = memberService.findWithPostById(memberId);
        return new MemberResponseWithPosts(findMember);
    }
}
