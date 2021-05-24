package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.model.comment.CommentSaveRequest;
import inu.appcenter.chanhee.model.comment.CommentUpdateRequest;
import inu.appcenter.chanhee.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/members/{memberId}/posts/{postId}/comments")
    public ResponseEntity saveComment(@PathVariable Long memberId, @PathVariable Long postId,
                                      @RequestBody CommentSaveRequest commentSaveRequest) {

        Long commentId = commentService.saveComment(memberId, postId, commentSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentId);
    }

    // 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long commentId,
                                        @RequestBody CommentUpdateRequest commentUpdateRequest) {

        commentService.updateComment(commentId, commentUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
