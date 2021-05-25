package inu.appcenter.yunah.model.comment;

import inu.appcenter.yunah.domain.Comment;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    private String content;

    public CommentDto(Comment comment) {

        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
