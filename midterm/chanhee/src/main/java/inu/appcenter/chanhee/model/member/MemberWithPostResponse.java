package inu.appcenter.chanhee.model.member;

import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.domain.member.MemberStatus;
import inu.appcenter.chanhee.model.post.PostDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberWithPostResponse {

    private Long memberId;

    private String email;

    private String password;

    private Integer age;

    private String name;

    private MemberStatus status;

    private List<PostDto> posts;

    public MemberWithPostResponse(Member member) {
        this.memberId = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.age = member.getAge();
        this.name = member.getName();
        this.status = member.getStatus();
        this.posts = member.getPostList().stream()
                .map(post -> new PostDto(post))
                .collect(Collectors.toList());

    }
}
