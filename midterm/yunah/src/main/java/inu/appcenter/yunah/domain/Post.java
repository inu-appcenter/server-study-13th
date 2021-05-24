package inu.appcenter.yunah.domain;

import inu.appcenter.yunah.domain.status.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
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

    // 게시글(post) : 회원(member) = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 게시글(post) : 카테고리(category) = N : 1
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "catagory_id")
    private Category category;

    // 게시글(post) : 댓글(comment) = 1 : N
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();


    public static Post savePost(String title, String content, Member member, Category category) {

        Post post = new Post();
        post.title = title;
        post.content = content;
        post.status = PostStatus.ACTIVE;
        post.member = member;
        post.category = category;
        return post;
    }


    public void updatePost(String title, String content) {

        this.title = title;
        this.content = content;
    }

    public void deletePost(Post post) {
        this.status = PostStatus.DELETED;
    }
}
