package inu.appcenter.jaekwon.model.member;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Min(value = 1)
    private Integer age;

    @NotBlank
    private String name;
}
