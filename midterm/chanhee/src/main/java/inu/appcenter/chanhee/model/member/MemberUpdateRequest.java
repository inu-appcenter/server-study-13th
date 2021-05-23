package inu.appcenter.chanhee.model.member;

import lombok.Data;

@Data
public class MemberUpdateRequest {

    // 나이, 이름만 변경
    private Integer age;

    private String name;
}
