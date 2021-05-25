package inu.appcenter.yunah.service;

import inu.appcenter.yunah.domain.Comment;
import inu.appcenter.yunah.domain.Member;
import inu.appcenter.yunah.domain.Post;
import inu.appcenter.yunah.exception.CommentException;
import inu.appcenter.yunah.exception.MemberException;
import inu.appcenter.yunah.exception.PostException;
import inu.appcenter.yunah.model.comment.CommentSaveRequest;
import inu.appcenter.yunah.model.comment.CommentUpdateRequest;
import inu.appcenter.yunah.repository.CommentRepository;
import inu.appcenter.yunah.repository.MemberRepository;
import inu.appcenter.yunah.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public void save(Long memberId, Long postId, CommentSaveRequest commentSaveRequest) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다."));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("존재하지 않는 게시글입니다."));

        Comment comment = Comment.saveComment(commentSaveRequest.getContent(), member, post);
        commentRepository.save(comment);
    }

    @Transactional
        public void update(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("존재하지 않는 댓글입니다."));
        comment.updateComment(commentUpdateRequest.getContent());
    }

    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("존재하지 않는 댓글입니다."));
        commentRepository.delete(comment);
    }
}
