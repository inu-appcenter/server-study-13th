package inu.appcenter.study3_1.controller;

import inu.appcenter.study3_1.config.security.JwtTokenProvider;
import inu.appcenter.study3_1.config.security.LoginMember;
import inu.appcenter.study3_1.domain.Member;
import inu.appcenter.study3_1.model.member.MemberLoginRequest;
import inu.appcenter.study3_1.model.member.MemberResponseWithOrderAndProduct;
import inu.appcenter.study3_1.model.member.MemberSaveRequest;
import inu.appcenter.study3_1.model.member.MemberUpdateRequest;
import inu.appcenter.study3_1.service.MemberService;
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


    //  회원 생성
    @PostMapping("/members")
    public ResponseEntity saveMember(@RequestBody @Valid MemberSaveRequest memberSaveRequest){
        memberService.saveMember(memberSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //  회원 로그인
    @PostMapping("/members/login")
    public ResponseEntity<String> loginMember(@RequestBody MemberLoginRequest memberLoginRequest){

        Member findMember = memberService.loginMember(memberLoginRequest);
        String jwtToken = jwtTokenProvider.createToken(String.valueOf(findMember.getId()));

        return ResponseEntity.ok(jwtToken);
    }

    //  회원 수정(이름만)
    @PatchMapping("/members")
    public ResponseEntity updateMember(@LoginMember User user,
                                        @RequestBody @Valid MemberUpdateRequest memberUpdateRequest){
        Long memberId = Long.valueOf(user.getUsername());
        memberService.updateMember(memberId, memberUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  회원 삭제
    @DeleteMapping("/members")
    public ResponseEntity deleteMember(@LoginMember User user){
        Long memberId = Long.valueOf(user.getUsername());
        memberService.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  회원 전체 조회(주문 목록,상품 함께)
    @GetMapping("/members")
    public List<MemberResponseWithOrderAndProduct> findWithOrderAndProductAllMember(){
        List<MemberResponseWithOrderAndProduct> result = memberService.findAll();
        return result;
    }

    //  회원 단건 조회(주문 목록, 상품 함께)
    @GetMapping("/members/{memberId}")
    public MemberResponseWithOrderAndProduct findWithOrderByMemberId(@PathVariable Long memberId){
        Member findMember = memberService.findById(memberId);
        return new MemberResponseWithOrderAndProduct(findMember);
    }
}
