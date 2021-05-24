package com.example.midterm.model.post;

import com.example.midterm.domain.Post;
import com.example.midterm.model.comment.CommentDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 처음에, private Member member로 작성했다가,
 * post -> member -> post -> member... 무한 반복하는 문제 발생
 * 이후, member 대신 String memberName으로 수정
 */
@Data
public class PostResponse {

    private String title;
    private String content;
    private String memberName;
    private List<CommentDTO> commentList;

    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.memberName = post.getMember().getName();

        /**
         * 그냥 List<Comment>로 return해도 괜찮으려나요???
         */
        this.commentList = post.getCommentList().stream().map(CommentDTO::new).collect(Collectors.toList());
    }
}
