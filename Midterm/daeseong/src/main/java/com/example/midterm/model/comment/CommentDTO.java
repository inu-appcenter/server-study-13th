package com.example.midterm.model.comment;

import com.example.midterm.domain.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private String content;
    private LocalDateTime updateAt;

    public CommentDTO(Comment comment) {
        this.content = comment.getContent();
        this.updateAt = comment.getUpdateAt();
    }
}
