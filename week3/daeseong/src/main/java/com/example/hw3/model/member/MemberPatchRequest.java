package com.example.hw3.model.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberPatchRequest {

    @NotBlank
    private String name;
}
