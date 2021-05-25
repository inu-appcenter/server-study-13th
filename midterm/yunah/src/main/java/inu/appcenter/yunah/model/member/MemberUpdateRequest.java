package inu.appcenter.yunah.model.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberUpdateRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer age;
}
