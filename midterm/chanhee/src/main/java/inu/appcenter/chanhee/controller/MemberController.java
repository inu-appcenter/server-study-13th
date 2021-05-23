package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.model.member.MemberWithPostResponse;
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

    // 회원 탙퇴
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId) {

        memberService.deleteMember(memberId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 ID 조회(회원 상태가 삭제 상태가 아닌 회원들만 조회)
    // 회원이 작성한 게시글 리스트까지 같이 조회(삭제 상태가 아닌 게시글만)
    @GetMapping("/members/{memberId}")
    public List<MemberWithPostResponse> findMemberById(@PathVariable Long memberId) {
        List<Member> members = memberService.findMemberById(memberId);

        List<MemberWithPostResponse> memberResponseList = members.stream()
                .map(member -> new MemberWithPostResponse(member))
                .collect(Collectors.toList());

        return memberResponseList;
    }
}
