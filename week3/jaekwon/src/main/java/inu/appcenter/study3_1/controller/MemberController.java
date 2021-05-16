package inu.appcenter.study3_1.controller;

import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.model.member.MemberResponseWithOrder;
import inu.appcenter.study3_1.model.member.MemberSaveRequest;
import inu.appcenter.study3_1.model.member.MemberUpdateRequest;
import inu.appcenter.study3_1.service.MemberService;
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

    //  회원 생성
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest){
        Long memberId = memberService.saveMember(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    //  회원 수정(이름만)
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId,
                                       @RequestBody @Valid MemberUpdateRequest memberUpdateRequest){
        memberService.updateMember(memberId, memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  회원 삭제
    @DeleteMapping("/members/{memberId}")
    public ResponseEntity deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  회원 전체 조회(주문 목록 함께)
    @GetMapping("/members")
    public List<MemberResponseWithOrder> findWithOrderAllMember(){
        List<Member> findWithOrderAllMember = memberService.findAll();
        List<MemberResponseWithOrder> findAll = findWithOrderAllMember.stream().map(member -> new MemberResponseWithOrder(member))
                .collect(Collectors.toList());
        return findAll;
    }

    //  회원 단건 조회(주문 목록 함께)
    @GetMapping("/members/{memberId}")
    public MemberResponseWithOrder findWithOrderByMemberId(@PathVariable Long memberId){
        Member findMember = memberService.findById(memberId);
        return new MemberResponseWithOrder(findMember);
    }
}
