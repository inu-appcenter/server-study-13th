package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.config.security.JwtTokenProvider;
import inu.appcenter.chanhee.config.security.LoginMember;
import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.model.member.MemberLoginRequest;
import inu.appcenter.chanhee.model.member.MemberResponse;
import inu.appcenter.chanhee.model.member.MemberSaveRequest;
import inu.appcenter.chanhee.model.member.MemberUpdateRequest;
import inu.appcenter.chanhee.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원 등록
    @PostMapping("/members")
    public ResponseEntity saveMember(@Valid @RequestBody MemberSaveRequest memberSaveRequest) {

        Long memberId = memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    // 회원 로그인
    @PostMapping("members/login")
    public ResponseEntity<String> loginMember(@Valid @RequestBody MemberLoginRequest memberLoginRequest) {

        // 서버에 저장된 회원 정보와 로그인 요청 정보가 일치하는지 확인
        Member findMember = memberService.loginMember(memberLoginRequest);

        // 로그인 요청 정보가 일치한다면 회원 ID를 사용하여 토큰 생성
        String token = jwtTokenProvider.createToken(String.valueOf(findMember.getId()));

        return ResponseEntity.ok(token);
    }

    // 회원 수정
    @PatchMapping("/members")
    public ResponseEntity updateMember(@LoginMember User user,
                                       @RequestBody MemberUpdateRequest memberUpdateRequest) {

        Long id = Long.valueOf(user.getUsername());

        memberService.updateMember(id, memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 탈퇴
    @DeleteMapping("/members")
    public ResponseEntity deleteMember(@LoginMember User user) {

        Long id = Long.valueOf(user.getUsername());

        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원 목록 조회 ( ADMIN 권한을 가진 사용자만 허용 )
    @GetMapping("/members")
    public List<MemberResponse> findAll() {

        List<Member> members = memberService.findAll();
        List<MemberResponse> memberResponseList = members.stream()
                .map(member -> new MemberResponse(member))
                .collect(Collectors.toList());

        return memberResponseList;
    }

    // 회원 ID 조회 = 회원 상세 조회
    @GetMapping("/members/{memberId}")
    public MemberResponse findMemberWithOrderById(@PathVariable Long memberId) {

        Member member = memberService.findMemberWithOrderById(memberId);
        return new MemberResponse(member);
    }
}
