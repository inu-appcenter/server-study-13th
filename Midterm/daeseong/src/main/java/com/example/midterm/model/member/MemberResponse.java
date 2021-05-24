package com.example.midterm.model.member;

import com.example.midterm.domain.Member;
import com.example.midterm.domain.status.MemberStatus;
import com.example.midterm.model.post.PostDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponse {

    private Long id;
    private String email;
    private int age;
    private String name;
    private MemberStatus status;
    private List<PostDTO> postList;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.status = member.getStatus();
        this.postList = member.getPostList().stream().map(PostDTO::new).collect(Collectors.toList());
    }
}
