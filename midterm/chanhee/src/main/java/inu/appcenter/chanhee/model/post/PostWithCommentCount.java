package inu.appcenter.chanhee.model.post;

import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.Post.PostStatus;
import lombok.Data;

@Data
public class PostWithCommentCount {

    private Long postId;

    private Long categoryId;

    private String categoryName;

    private String writeMember;

    private String title;

    private String content;

    private PostStatus status;

    private Long counts;

    public PostWithCommentCount(Post post, Long count) {
        this.postId = post.getId();
        this.categoryId = post.getCategory().getId();
        this.categoryName = post.getCategory().getContent();
        this.writeMember = post.getMember().getEmail();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
        this.counts = count;
    }

}
