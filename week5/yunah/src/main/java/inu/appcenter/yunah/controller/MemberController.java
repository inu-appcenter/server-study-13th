package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.config.security.JwtTokenProvider;
import inu.appcenter.yunah.config.security.LoginMember;
import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.model.member.MemberLoginRequest;
import inu.appcenter.yunah.model.member.MemberResponse;
import inu.appcenter.yunah.model.member.MemberSaveRequest;
import inu.appcenter.yunah.model.member.MemberUpdateRequest;
import inu.appcenter.yunah.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    /*
    회원 가입 (권한 필요 X)
     */
    @PostMapping("/members")
    public ResponseEntity<Void> saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest) {

        memberService.saveMember(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    회원 로그인 ( 권한 필요 X )
     */
    @PostMapping("/members/login")
    public ResponseEntity<String> loginMember(@RequestBody @Valid MemberLoginRequest memberLoginRequest) {
        Member findMember = memberService.loginMember(memberLoginRequest);
        String jwtToken = jwtTokenProvider.createToken(String.valueOf(findMember.getId()));
        return ResponseEntity.ok(jwtToken);
    }

    /*
    회원 수정 ( 이름, 비밀번호 변경 )
     */
    @PatchMapping("/members")
    public ResponseEntity updateMember(@LoginMember User user, @RequestBody @Valid MemberUpdateRequest memberUpdateRequest) {
        String memberId = user.getUsername();
        memberService.updateMember(Long.parseLong(memberId), memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    회원 탈퇴
     */
    @DeleteMapping("/members")
    public ResponseEntity deleteMember(@LoginMember User user) {
        String memberId = user.getUsername();
        memberService.deleteMember(Long.parseLong(memberId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    회원 목록 조회 ( ADMIN 권한을 가진 사용자만 허용 )  // 회원 리스트 + 회원의 주문 리스트 + 주문한 상품까지
     */
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> findAllMember() {
        List<Member> members = memberService.findAllMember();
        List<MemberResponse> memberList = members.stream().map(member -> MemberResponse.from(member)).collect(Collectors.toList());
        return ResponseEntity.ok(memberList);
    }

    /*
    회원 ID 조회 = 회원 상세 조회 ( 회원 + 회원의 주문 리스트 + 주문한 상품 까지 )
     */
    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponse> findMember(@PathVariable Long memberId) {
        Member member = memberService.findMember(memberId);
        return ResponseEntity.ok(MemberResponse.from(member));
    }
}
