package inu.appcenter.chanhee.model.post;

import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.Post.PostStatus;
import lombok.Data;

@Data
public class PostDto {

    private Long postId;

    private Long categoryId;

    private String title;

    private String content;

    private PostStatus status;

    public PostDto(Post post) {
        this.postId = post.getId();
        this.categoryId = post.getCategory().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
    }
}
