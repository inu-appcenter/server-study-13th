package com.example.midterm.domain;

import com.example.midterm.domain.status.PostStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    public static Post createPost(String title, String content, Member member, Category category) {
        Post post = new Post();

        post.title = title;
        post.content = content;
        post.status = PostStatus.POSTED;
        post.member = member;
        post.category = category;

        return post;
    }

    public void patchPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void deletePost() {
        this.status = PostStatus.DELETED;
    }
}
