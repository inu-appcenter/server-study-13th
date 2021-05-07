package inu.appcenter.jaekwon.model.member;

import inu.appcenter.jaekwon.domain.Member;
import lombok.Data;

@Data
public class MemberResponse {

    private Long memberId;
    private String email;
    private int age;
    private String name;

    public MemberResponse(Member member){
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
    }
}
