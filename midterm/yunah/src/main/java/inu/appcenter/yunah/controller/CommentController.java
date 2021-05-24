package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.model.comment.CommentSaveRequest;
import inu.appcenter.yunah.model.comment.CommentUpdateRequest;
import inu.appcenter.yunah.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /*
    댓글 생성
     */
    @PostMapping("/members/{memberId}/posts/{postId}/comments")
    public ResponseEntity saveComment(@PathVariable Long memberId, @PathVariable Long postId,
            @RequestBody @Valid CommentSaveRequest commentSaveRequest) {

        commentService.save(memberId, postId, commentSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    댓글 수정
     */
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentUpdateRequest commentUpdateRequest) {

        commentService.update(commentId, commentUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    댓글 삭제
     */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId) {

        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
