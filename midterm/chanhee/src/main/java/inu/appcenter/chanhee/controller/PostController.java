package inu.appcenter.chanhee.controller;

import inu.appcenter.chanhee.domain.Post.Post;
import inu.appcenter.chanhee.model.post.*;
import inu.appcenter.chanhee.service.PostService;
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

    // 게시글 생성
    @PostMapping("/members/{memberId}/categories/{categoryId}/posts")
    public ResponseEntity savePost(@PathVariable Long memberId, @PathVariable Long categoryId,
                                   @Valid @RequestBody PostSaveRequest postSaveRequest) {

        Long postId = postService.savePost(memberId, categoryId, postSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }

    // 게시글 수정
    @PatchMapping("/posts/{postId}")
    public ResponseEntity updatePost(@PathVariable Long postId,
                                     @RequestBody PostUpdateRequest postUpdateRequest) {

        postService.updatePost(postId, postUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {

        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 게시글 상세 조회 (ID 조회) -> 댓글까지 조회
    @GetMapping("/posts/{postId}")
    public PostResponse findPostById(@PathVariable Long postId) {

        Post post = postService.findPostById(postId);

        return new PostResponse(post);
    }

    // 카테고리 Id로 게시글 리스트 조회
    // 삭제상태가 아닌 게시글들만!, 게시글의 댓글 수 까지 같이 조회
    @GetMapping("/categories/{categoryId}/posts")
    public List<PostWithCommentCount> findPostByCategoryId(@PathVariable Long categoryId) {

        List<PostWithCommentCount> postWithComment = postService.findPostCommentByCategoryId(categoryId);
//        List<PostWithCommentCountResponse> postWithCommentCountDtoList = commentCount.stream()
//                                                                    .map(result -> new PostWithCommentCountResponse(result))
//                                                                    .collect(Collectors.toList());

        return postWithComment;
    }

}
