package inu.appcenter.yunah.model.post;

import inu.appcenter.yunah.domain.Post;
import inu.appcenter.yunah.domain.status.PostStatus;
import inu.appcenter.yunah.model.comment.CommentDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponse {

    private Long postId;

    private String title;

    private String content;

    private PostStatus status;

    List<CommentDto> commentList;

    public PostResponse(Post post) {

        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
        this.commentList = post.getCommentList().stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
    }
}
