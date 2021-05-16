package inu.appcenter.yunah.model.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberUpdateRequest {

    @NotBlank
    private String name;
}
