package com.example.hw2.model.member.request;

import lombok.Data;

@Data
public class MemberSaveRequest {
    private String email;
    private int age;
    private String name;
}