package inu.appcenter.yunah.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String content;

    // 댓글(comment) : 회원(member) = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 댓글(comment) : 게시글(post) = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public static Comment saveComment(String content, Member member, Post post) {

        Comment comment = new Comment();
        comment.content = content;
        comment.member = member;
        comment.post = post;
        return comment;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
