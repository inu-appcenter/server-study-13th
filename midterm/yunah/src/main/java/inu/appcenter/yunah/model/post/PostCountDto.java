package inu.appcenter.yunah.model.post;

import com.querydsl.core.annotations.QueryProjection;
import inu.appcenter.yunah.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCountDto {

    private Long id;

    private String title;

    private String content;

    private Long count;

    @QueryProjection
    public PostCountDto(Post post, Long count) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.count = count;
    }
}
