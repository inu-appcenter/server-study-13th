package inu.appcenter.jaekwon.service;

import com.querydsl.core.Tuple;
import inu.appcenter.jaekwon.domain.Category;
import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.exception.CategoryException;
import inu.appcenter.jaekwon.exception.MemberException;
import inu.appcenter.jaekwon.exception.PostException;
import inu.appcenter.jaekwon.model.post.PostSaveRequest;
import inu.appcenter.jaekwon.model.post.PostUpdateRequest;
import inu.appcenter.jaekwon.model.post.PostWithCommentCount;
import inu.appcenter.jaekwon.repository.CategoryRepository;
import inu.appcenter.jaekwon.repository.MemberRepository;
import inu.appcenter.jaekwon.repository.PostRepository;
import inu.appcenter.jaekwon.repository.query.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PostQueryRepository postQueryRepository;

    @Transactional
    public void savePost(Long memberId, Long categoryId, PostSaveRequest postSaveRequest) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        Category findCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryException("존재하지 않는 카테고리입니다."));


        Post post = Post.createPost(postSaveRequest.getTitle(), postSaveRequest.getContent(),
                                    findMember, findCategory);
        postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));
        post.update(postUpdateRequest.getTitle(), postUpdateRequest.getContent());
    }

    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));
        post.delete();
    }

    public Post findWithCommentsById(Long postId) {
        Post findPost = postRepository.findWithCommentsById(postId)
                .orElseThrow(() -> new PostException("게시글이 없습니다."));
        return findPost;
    }

    public List<PostWithCommentCount> findWithCountByCategoryId(Long categoryId) {
        List<PostWithCommentCount> postWithCommentCountsByCategoryId = postQueryRepository.findPostWithCommentCountsByCategoryId(categoryId);
        return postWithCommentCountsByCategoryId;
    }
}
