package com.example.midterm.service;

import com.example.midterm.advice.exception.CategoryException;
import com.example.midterm.advice.exception.MemberException;
import com.example.midterm.advice.exception.PostException;
import com.example.midterm.domain.Category;
import com.example.midterm.domain.Member;
import com.example.midterm.domain.Post;
import com.example.midterm.domain.status.CategoryStatus;
import com.example.midterm.domain.status.PostStatus;
import com.example.midterm.model.post.PostPatchRequest;
import com.example.midterm.model.post.PostSaveRequest;
import com.example.midterm.model.post.PostWithCommentCount;
import com.example.midterm.repository.CategoryRepository;
import com.example.midterm.repository.MemberRepository;
import com.example.midterm.repository.PostRepository;
import com.example.midterm.repository.query.CategoryQueryRepository;
import com.example.midterm.repository.query.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;
    private final CategoryQueryRepository categoryQueryRepository;

    @Transactional
    public void savePost(Long memberId, Long categoryId, PostSaveRequest postSaveRequest) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("No Member Exists"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("No Category Exists"));

        Post post = Post.createPost(postSaveRequest.getTitle(), postSaveRequest.getContent(), member, category);

        postRepository.save(post);
    }

    @Transactional
    public void patchPost(Long postId, PostPatchRequest postPatchRequest) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("No Post Exists"));

        post.patchPost(postPatchRequest.getTitle(), postPatchRequest.getContent());
    }

    @Transactional
    public void deletePost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("No Post Exists"));

        post.deletePost();
    }

    /**
     * 게시글 -> 작성한 회원 참조 -> 회원 목록에서 회원이 작성한 게시글 -> 게시글을 작성한 회원... 무한 반복되는 상황
     * 해결 완료. Member를 String memberName으로 수정.
     * 두 개 만든건 그냥 만들어보고 싶어서 만들었습니다.
     * @param postId want to find
     * @return Post's Info
     */
    public Post findPost(Long postId) {

        // In PostRepository
        Post temp = postRepository.findPostWithComment(postId)
                .orElseThrow(() -> new PostException("No Post Exists"));

        // In PostQueryRepository
        // Post post = postQueryRepository.findPostWithComment(postId);

        if (temp.getStatus().equals(PostStatus.DELETED)) {
            throw new PostException("Deleted Post");
        }

        return temp;
    }

    public List<PostWithCommentCount> findPostInCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("No Category Exists"));

        if (category.getStatus().equals(CategoryStatus.DEACTIVATED)) {
            throw new CategoryException("Deleted Category");
        }

        return postQueryRepository.findPostWithCommentCount(categoryId);
    }
}
