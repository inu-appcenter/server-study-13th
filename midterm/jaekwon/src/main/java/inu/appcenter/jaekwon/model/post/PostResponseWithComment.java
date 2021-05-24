package inu.appcenter.jaekwon.model.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.domain.PostStatus;
import inu.appcenter.jaekwon.model.comment.CommentDto;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponseWithComment {

    private Long id;
    private String title;
    private String content;
    private PostStatus status;
    private List<CommentDto> comments;

    public PostResponseWithComment(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
        this.comments = post.getCommentList().stream().map(comment -> new CommentDto(comment))
                        .collect(Collectors.toList());
    }
}
