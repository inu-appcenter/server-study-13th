package inu.appcenter.chanhee.model.member;

import lombok.Data;

@Data
public class MemberUpdateRequest {

    // 비밀번호와 이름만 변경 가능
    private String password;

    private String name;
}
