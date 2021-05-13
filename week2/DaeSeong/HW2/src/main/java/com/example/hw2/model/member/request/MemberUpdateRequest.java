package com.example.hw2.model.member.request;

import lombok.Data;

@Data
public class MemberUpdateRequest {
    private String email;
    private String name;
    private int age;
}
