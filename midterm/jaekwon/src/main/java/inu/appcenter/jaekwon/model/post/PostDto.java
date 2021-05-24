package inu.appcenter.jaekwon.model.post;

import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.domain.PostStatus;
import lombok.Data;

@Data
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private PostStatus status;

    public PostDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
    }
}
