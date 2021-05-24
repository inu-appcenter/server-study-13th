package inu.appcenter.chanhee.domain.comment;

import inu.appcenter.chanhee.domain.BaseEntity;
import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 댓글 생성
    public static Comment saveComment(String content, Member member, Post post) {
        Comment comment = new Comment();
        comment.content = content;
        comment.member = member;
        comment.post = post;

        return comment;
    }

    // 댓글 수정
    public void updateComment(String content) {
        this.content = content;
    }

}
