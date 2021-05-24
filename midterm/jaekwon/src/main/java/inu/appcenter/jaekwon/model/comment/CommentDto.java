package inu.appcenter.jaekwon.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import inu.appcenter.jaekwon.domain.Comment;
import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.model.member.MemberDto;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    private String content;

    //  누구의 댓글인지 알 수 있게
    private MemberDto member;

    public CommentDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.member = new MemberDto(comment.getMember());
    }
}
