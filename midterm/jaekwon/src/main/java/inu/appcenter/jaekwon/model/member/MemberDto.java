package inu.appcenter.jaekwon.model.member;

import inu.appcenter.jaekwon.domain.Member;
import lombok.Data;

@Data
public class MemberDto {

    private Long id;
    private String name;

    public MemberDto(Member member){
        this.id = member.getId();
        this.name = member.getName();
    }
}
