package com.example.hw5.model.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberPatchRequest {

    @NotBlank
    private String password;

    @NotBlank
    private String name;
}
