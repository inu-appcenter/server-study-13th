package inu.appcenter.study3_1.model.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
