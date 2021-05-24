package com.example.midterm.model.post;

import com.example.midterm.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWithCommentCount {

    private String title;
    private String content;
    private Long commentCount;

    public PostWithCommentCount(Post post, Long commentCount) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.commentCount = commentCount;
    }
}
