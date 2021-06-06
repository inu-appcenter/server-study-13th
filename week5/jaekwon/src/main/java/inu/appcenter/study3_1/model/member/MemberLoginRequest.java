package inu.appcenter.study3_1.model.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
