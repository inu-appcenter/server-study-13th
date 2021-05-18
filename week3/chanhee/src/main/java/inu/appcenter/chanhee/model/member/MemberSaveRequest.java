package inu.appcenter.chanhee.model.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveRequest {

    @Email                  // 이메일 조건 검사
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;
}
