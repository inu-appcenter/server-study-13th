package inu.appcenter.yunah.model.member;

import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.status.MemberStatus;
import inu.appcenter.yunah.model.post.PostDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberWithPostResponse {

    private Long memberId;

    private String email;

    private String password;

    private String name;

    private int age;

    private MemberStatus status;

    List<PostDto> postList;

    public MemberWithPostResponse(Member member) {

        this.memberId = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.age = member.getAge();
        this.status = member.getStatus();
        this.postList = member.getPostList().stream().map(post -> new PostDto(post)).collect(Collectors.toList());
    }
}
