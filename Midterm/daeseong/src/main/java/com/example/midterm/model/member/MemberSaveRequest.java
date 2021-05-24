package com.example.midterm.model.member;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class MemberSaveRequest {

    @Email
    private String email;

    @NonNull
    private String password;

    @NotBlank
    private int age;

    @NonNull
    private String name;
}
