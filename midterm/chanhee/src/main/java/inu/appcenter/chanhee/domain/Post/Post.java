package inu.appcenter.chanhee.domain.Post;

import inu.appcenter.chanhee.domain.BaseEntity;
import inu.appcenter.chanhee.domain.category.Category;
import inu.appcenter.chanhee.domain.comment.Comment;
import inu.appcenter.chanhee.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    // 게시글 등록
   public static Post savePost(String title, String content, Member member, Category category) {
        Post post = new Post();
        post.title = title;
        post.content = content;
        post.member = member;
        post.category = category;
        post.status = PostStatus.POST;

        return post;
    }

    // 게시글 수정
    public void updatePost(String title, String content) {
       this.title = title;
       this.content = content;
    }

    // 게시글 삭제
    public void deletePost(PostStatus status) {
       this.status = PostStatus.DELETE;
    }
}
