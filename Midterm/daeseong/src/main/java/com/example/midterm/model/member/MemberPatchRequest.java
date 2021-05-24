package com.example.midterm.model.member;

import lombok.Data;
import lombok.NonNull;

@Data
public class MemberPatchRequest {

    @NonNull
    private int age;

    @NonNull
    private String name;
}
