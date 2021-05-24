package inu.appcenter.jaekwon.model.member;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class MemberUpdateRequest {

    @Min(value = 1)
    private int age;

    @NotBlank
    private String name;
}
