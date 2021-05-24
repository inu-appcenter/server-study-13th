package inu.appcenter.jaekwon.model.member;

import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.model.post.PostDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponseWithPosts {

    private Long id;
    private String email;
    private  int age;
    private String name;
    private List<PostDto> posts;

    public MemberResponseWithPosts(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.age = member.getAge();
        this.name = member.getName();
        this.posts = member.getPostList().stream()
                .map(post -> new PostDto(post))
                .collect(Collectors.toList());
    }
}
