package inu.appcenter.chanhee.model.post;

import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.Post.PostStatus;
import inu.appcenter.chanhee.model.comment.CommentDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponse {

    private Long postId;

    private Long categoryId;

    private String writeMember;

    private String title;

    private String content;

    private PostStatus status;

    private List<CommentDto> comments;

    public PostResponse(Post post) {
        this.postId = post.getId();
        this.categoryId = post.getCategory().getId();
        this.writeMember = post.getMember().getEmail();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
        this.comments = post.getCommentList().stream()
                .map(comment -> new CommentDto(comment))
                .collect(Collectors.toList());
    }
}
