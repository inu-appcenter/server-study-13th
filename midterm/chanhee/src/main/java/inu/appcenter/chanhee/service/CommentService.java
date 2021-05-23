package inu.appcenter.chanhee.service;

import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.domain.comment.Comment;
import inu.appcenter.chanhee.domain.member.Member;
import inu.appcenter.chanhee.exception.CommentException;
import inu.appcenter.chanhee.exception.MemberException;
import inu.appcenter.chanhee.exception.PostException;
import inu.appcenter.chanhee.model.comment.CommentSaveRequest;
import inu.appcenter.chanhee.model.comment.CommentUpdateRequest;
import inu.appcenter.chanhee.repository.CommentRepository;
import inu.appcenter.chanhee.repository.MemberRepository;
import inu.appcenter.chanhee.repository.PostRepository;
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

    // 댓글 생성
    @Transactional
    public Long saveComment(Long memberId, Long postId, CommentSaveRequest commentSaveRequest) {

        // 회원 id가 존재하는지 확인
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));

        // 게시글 id가 존재하는지 확인
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));

        // 회원과 게시글 모두가 존재한다면
        Comment comment = Comment.saveComment(commentSaveRequest.getContent(), findMember, findPost);
        Comment savedComment = commentRepository.save(comment);

        return savedComment.getId();
    }

    // 댓글 수정
    @Transactional
    public void updateComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {

        // 댓글 id가 존재하는지 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("존재하지 않는 댓글입니다."));

        // 댓글이 존재한다면
        comment.updateComment(commentUpdateRequest.getContent());
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {

        // 댓글 id가 존재하는지 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("존재하지 않는 댓글입니다."));

        // 댓글이 존재한다면
        commentRepository.delete(comment);
    }
}
