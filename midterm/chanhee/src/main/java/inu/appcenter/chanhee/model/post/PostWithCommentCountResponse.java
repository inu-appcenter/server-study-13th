package inu.appcenter.chanhee.model.post;

import inu.appcenter.chanhee.domain.Post.PostStatus;
import lombok.Data;

@Data
public class PostWithCommentCountResponse {

    private Long postId;

    private Long categoryId;

    private String categoryName;

    private String writeMember;

    private String title;

    private String content;

    private PostStatus status;

    private Long counts;

    public PostWithCommentCountResponse (PostWithCommentCountDto postWithCommentCountDto) {
        this.postId = postWithCommentCountDto.getPost().getId();
        this.categoryId = postWithCommentCountDto.getPost().getCategory().getId();
        this.categoryName = postWithCommentCountDto.getPost().getCategory().getContent();
        this.writeMember = postWithCommentCountDto.getPost().getMember().getEmail();
        this.title = postWithCommentCountDto.getPost().getTitle();
        this.content = postWithCommentCountDto.getPost().getContent();
        this.status = postWithCommentCountDto.getPost().getStatus();
        this.counts = postWithCommentCountDto.getCount();
    }
}
