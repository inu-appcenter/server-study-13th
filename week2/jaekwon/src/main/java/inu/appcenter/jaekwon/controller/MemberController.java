package inu.appcenter.jaekwon.controller;

import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.model.member.MemberResponse;
import inu.appcenter.jaekwon.model.member.MemberResponseWithTodo;
import inu.appcenter.jaekwon.model.member.MemberSaveRequest;
import inu.appcenter.jaekwon.model.member.MemberUpdateRequest;
import inu.appcenter.jaekwon.service.MemberService;
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

    //  회원생성
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody MemberSaveRequest memberSaveRequest){
        memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //  회원수정
    @PatchMapping("/members/{memberId}")
    public ResponseEntity updateMember(@PathVariable Long memberId,
                                       @RequestBody MemberUpdateRequest memberUpdateRequest){
        memberService.updateMember(memberId, memberUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  회원리스트만 조회
    @GetMapping("/members")
    public List<MemberResponse> findAllMemeber(){
        List<Member> members = memberService.findAll();

        List<MemberResponse> memberResponseList = members.stream().map(member -> new MemberResponse(member))
                .collect(Collectors.toList());

        return memberResponseList;
    }

    //  회원(한명)의 todo까지 같이 조회
    @GetMapping("/members/{memberId}")
    public MemberResponseWithTodo findById(@PathVariable Long memberId){
        Member member = memberService.findById(memberId);

        return new MemberResponseWithTodo(member);
    }
}
