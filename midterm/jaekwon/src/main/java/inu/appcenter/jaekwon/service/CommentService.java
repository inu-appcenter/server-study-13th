package inu.appcenter.jaekwon.service;

import inu.appcenter.jaekwon.domain.Comment;
import inu.appcenter.jaekwon.domain.Member;
import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.exception.CommentException;
import inu.appcenter.jaekwon.exception.MemberException;
import inu.appcenter.jaekwon.exception.PostException;
import inu.appcenter.jaekwon.model.comment.CommentSaveRequest;
import inu.appcenter.jaekwon.model.comment.CommentUpdateRequest;
import inu.appcenter.jaekwon.repository.CommentRepository;
import inu.appcenter.jaekwon.repository.MemberRepository;
import inu.appcenter.jaekwon.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void saveComment(Long memberId, Long postId, CommentSaveRequest commentSaveRequest) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));

        Comment comment = Comment.create(commentSaveRequest.getContent(), findMember, findPost);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("존재하지 않는 댓글입니다."));
        findComment.update(commentUpdateRequest.getContent());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comment findComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("존재하지 않는 댓글입니다."));
        commentRepository.delete(findComment);
    }
}
