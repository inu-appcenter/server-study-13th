package inu.appcenter.jaekwon.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inu.appcenter.jaekwon.domain.Comment;
import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.domain.PostStatus;
import inu.appcenter.jaekwon.model.comment.CommentDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostWithCommentCount {

    private Long id;
    private String title;
    private String content;
    private PostStatus status;

    // 댓글 리스트까지 같이 조회하려면 추가
    //private List<CommentDto> commentList;

    private Long count;

    public PostWithCommentCount(Post post, Long count){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
        //this.commentList = post.getCommentList().stream().map(comment -> new CommentDto(comment))
        //                    .collect(Collectors.toList());
        this.count = count;
    }
}
