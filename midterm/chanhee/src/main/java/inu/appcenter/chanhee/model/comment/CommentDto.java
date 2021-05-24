package inu.appcenter.chanhee.model.comment;

import inu.appcenter.chanhee.domain.comment.Comment;
import lombok.Data;

@Data
public class CommentDto {

    private Long commentId;

    private String content;

    public CommentDto(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
    }
}
