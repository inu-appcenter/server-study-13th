package com.example.midterm.controller;

import com.example.midterm.domain.Post;
import com.example.midterm.model.post.PostPatchRequest;
import com.example.midterm.model.post.PostResponse;
import com.example.midterm.model.post.PostSaveRequest;
import com.example.midterm.model.post.PostWithCommentCount;
import com.example.midterm.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/members/{memberId}/categories/{categoryId}/posts")
    public ResponseEntity<Void> savePost(@PathVariable Long memberId, @PathVariable Long categoryId, @RequestBody @Valid PostSaveRequest postSaveRequest) {

        postService.savePost(memberId, categoryId, postSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/posts/{postId}")
    public ResponseEntity<Void> patchPost(@PathVariable Long postId, @RequestBody @Valid PostPatchRequest postPatchRequest) {

        postService.patchPost(postId, postPatchRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/posts/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        Post post = postService.findPost(postId);

        return new PostResponse(post);
    }

    @GetMapping("/categories/{categoryId}/posts")
    public List<PostWithCommentCount> getPosts(@PathVariable Long categoryId) {
        List<PostWithCommentCount> postList = postService.findPostInCategory(categoryId);

        return postList;
    }
}
