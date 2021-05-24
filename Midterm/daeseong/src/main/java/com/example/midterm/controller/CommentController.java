package com.example.midterm.controller;

import com.example.midterm.model.comment.CommentPatchRequest;
import com.example.midterm.model.comment.CommentSaveRequest;
import com.example.midterm.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/members/{memberId}/posts/{postId}/comments")
    public ResponseEntity<Void> saveComment(@PathVariable Long memberId, @PathVariable Long postId, @RequestBody @Valid CommentSaveRequest commentSaveRequest) {
        commentService.saveComment(memberId, postId, commentSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> patchComment(@PathVariable Long commentId, @RequestBody @Valid CommentPatchRequest commentPatchRequest) {
        commentService.patchComment(commentId, commentPatchRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
