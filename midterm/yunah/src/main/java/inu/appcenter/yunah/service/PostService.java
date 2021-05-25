package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Category;
import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.Post;
import inu.appcenter.yunah.domain.status.CategoryStatus;
import inu.appcenter.yunah.domain.status.PostStatus;
import inu.appcenter.yunah.exception.CategoryException;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.exception.PostException;
import inu.appcenter.yunah.model.post.PostCountDto;
import inu.appcenter.yunah.model.post.PostSaveRequest;
import inu.appcenter.yunah.model.post.PostUpdateRequest;
import inu.appcenter.yunah.repository.CategoryRepository;
import inu.appcenter.yunah.repository.MemberRepository;
import inu.appcenter.yunah.repository.PostRepository;
import inu.appcenter.yunah.repository.query.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PostQueryRepository postQueryRepository;

    @Transactional
    public void save(Long memberId, Long categoryId, PostSaveRequest postSaveRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("존재하지 않는 카테고리입니다."));

        Post post = Post.savePost(postSaveRequest.getTitle(), postSaveRequest.getContent(), member, category);
        postRepository.save(post);
    }

    @Transactional
    public void update(Long postId, PostUpdateRequest postUpdateRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));
        post.updatePost(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));
        post.deletePost(post);
    }

    public Post findPostWithCommentById(Long postId) {

        Post postById = postQueryRepository.findPostById(postId);

        return postById;
    }

    public List<PostCountDto> findPostList(Long categoryId) {

        List<PostCountDto> postByCategory = postQueryRepository.findPostByCategory(categoryId);

        return postByCategory;
    }
}

