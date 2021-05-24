package com.example.midterm.service;

import com.example.midterm.advice.exception.CommentException;
import com.example.midterm.advice.exception.MemberException;
import com.example.midterm.advice.exception.PostException;
import com.example.midterm.domain.Comment;
import com.example.midterm.domain.Member;
import com.example.midterm.domain.Post;
import com.example.midterm.model.comment.CommentPatchRequest;
import com.example.midterm.model.comment.CommentSaveRequest;
import com.example.midterm.repository.CommentRepository;
import com.example.midterm.repository.MemberRepository;
import com.example.midterm.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Long memberId, Long postId, CommentSaveRequest commentSaveRequest) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException("No Member Exists"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostException("No Post Exists"));

        Comment comment = Comment.createComment(commentSaveRequest.getContent(), member, post);

        commentRepository.save(comment);
    }

    @Transactional
    public void patchComment(Long commentId, CommentPatchRequest commentPatchRequest) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("No Comment Exists"));

        comment.patch(commentPatchRequest.getContent());
    }

    public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("No Comment Exists"));

        commentRepository.delete(comment);
    }
}
