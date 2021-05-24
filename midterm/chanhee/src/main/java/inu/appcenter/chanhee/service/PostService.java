package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.category.Category;
import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.exception.CategoryException;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.exception.PostException;
import inu.appcenter.chanhee.model.post.*;
import inu.appcenter.chanhee.repository.CategoryRepository;
import inu.appcenter.chanhee.repository.MemberRepository;
import inu.appcenter.chanhee.repository.PostRepository;
import inu.appcenter.chanhee.repository.query.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PostQueryRepository postQueryRepository;

    // 게시글 생성
    @Transactional
    public Long savePost(Long memberId, Long categoryId, PostSaveRequest postSaveRequest) {

        // 회원 id가 존재하는지확인
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 카테고리 id가 존재하는지 확인
        Category findCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("존재하지 않는 카테고리 입니다."));

        // 회원과 카테고리 둘 다 존재한다면
        Post post = Post.savePost(postSaveRequest.getTitle(), postSaveRequest.getContent(),
                                    findMember, findCategory);

        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, PostUpdateRequest postUpdateRequest) {

        // 게시글 id가 존재하는지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));

        // 게시글이 존재한다면
        post.updatePost(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId) {

        // 게시글 id가 존재하는지 확인
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));

        // 게시글이 존재한다면
        post.deletePost(post.getStatus());
    }

    // 게시글 상세 조회 (ID 조회) -> 댓글까지 조회
    public Post findPostById(Long postId) {

        // 게시글 id가 존재하는지 확인
        Post post = postQueryRepository.findWithCommentById(postId);

        return post;
    }

    // 카테고리 Id로 게시글 리스트 조회
    // 삭제상태가 아닌 게시글들만!, 게시글의 댓글 수 까지 같이 조회
    public List<PostWithCommentCount> findPostCommentByCategoryId(Long categoryId) {

        // 카테고리 id가 존재하는지 확인
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("존재하지 않는 카테고리입니다."));

        // 해당 카테고리의 게시글과 댓글 수 가져오기
        List<PostWithCommentCount> postWithCommentCounts = postQueryRepository.findPostwithCommentCountsByCategoryId(categoryId);

        return postWithCommentCounts;
    }

}
