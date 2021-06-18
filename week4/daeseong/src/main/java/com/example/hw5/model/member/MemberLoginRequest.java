package com.example.hw5.model.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginRequest {

    private String email;

    @NotBlank
    private String password;
}
