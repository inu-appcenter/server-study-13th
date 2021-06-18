package com.example.hw5.controller;

import com.example.hw5.config.security.JwtTokenProvider;
import com.example.hw5.config.security.LoginMember;
import com.example.hw5.domain.Member;
import com.example.hw5.model.member.MemberLoginRequest;
import com.example.hw5.model.member.MemberPatchRequest;
import com.example.hw5.model.member.MemberResponse;
import com.example.hw5.model.member.MemberSaveRequest;
import com.example.hw5.service.MemberService;
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

    @PostMapping("/members")  // 회원 가입
    public ResponseEntity<Void> saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {
        memberService.saveMember(memberSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 로그인
     * 회원의 상태가 DELETED면, 로그인 불가능
     * ADMIN은 email 형식이 아니므로, 로그인할 땐 email 형식 검사를 하지 않음
     *
     * @param memberLoginRequest contains email, password
     * @return OK or ERROR
     */
    @PostMapping("/members/login")
    public ResponseEntity<String> loginMember(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {

        Member findMember = memberService.loginMember(memberLoginRequest);

        String jwtToken = jwtTokenProvider.createToken(String.valueOf(findMember.getId()));

        return ResponseEntity.ok(jwtToken);
    }

    @PatchMapping("/members")  // 회원 정보 변경. email을 제외한 password, name 값 변경 가능
    public ResponseEntity<Void> patchMember(@RequestBody @Valid MemberPatchRequest memberPatchRequest, @LoginMember User user) {

        memberService.patchMember(memberPatchRequest, user.getUsername());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/members")  // 회원 삭제. MemberStatus 변경
    public ResponseEntity<Void> deleteMember(@LoginMember User user) {

        memberService.deleteMember(user.getUsername());

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 회원 전체 조회
     * ADMIN ONLY
     * Member, Order와 Product 상태 상관없이 조회 -> ADMIN이 조회하는 것이므로
     *
     * @return All Member
     */
    @GetMapping("/members")  // TODO admin만 조회 가능 추가
    public List<MemberResponse> findAllMembers(@LoginMember User user) {

        List<Member> members = memberService.findAll(user);

        return members.stream().map(MemberResponse::new).collect(Collectors.toList());
    }

    /**
     * Find one Member whose STATUS is ACTIVATED
     * ANYONE AVAILABLE
     *
     * @param memberId Long
     * @return MemberResponse
     */
    @GetMapping("/members/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId) {

        Member member = memberService.findMember(memberId);

        return new MemberResponse(member);
    }
}
