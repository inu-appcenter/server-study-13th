package inu.appcenter.chanhee.model.post;

import inu.appcenter.chanhee.domain.Post.Post;
import lombok.Data;

@Data
public class PostWithCommentCountDto {

    private Post post;

    private Long count;

}
