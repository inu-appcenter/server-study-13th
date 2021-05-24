package inu.appcenter.yunah.controller;

import inu.appcenter.yunah.domain.Post;
import inu.appcenter.yunah.model.post.PostCountDto;
import inu.appcenter.yunah.model.post.PostResponse;
import inu.appcenter.yunah.model.post.PostSaveRequest;
import inu.appcenter.yunah.model.post.PostUpdateRequest;
import inu.appcenter.yunah.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /*
    게시글 생성
     */
    @PostMapping("/members/{memberId}/categories/{categoryId}/posts")
    public ResponseEntity savePost(@PathVariable Long memberId, @PathVariable Long categoryId, @RequestBody @Valid PostSaveRequest postSaveRequest) {

        postService.save(memberId, categoryId, postSaveRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*
    게시글 수정 ( 제목, 내용 변경)
     */
    @PatchMapping("/posts/{postId}")
    public ResponseEntity updatePost(@PathVariable Long postId, @RequestBody @Valid PostUpdateRequest postUpdateRequest) {

        postService.update(postId, postUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    게시글 삭제 ( 상태 변경 )
     */
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId) {

        postService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*
    게시글 ID 조회 ( 댓글 목록까지 함께 조회 )
     */
    @GetMapping("/posts/{postId}")
    public PostResponse findById(@PathVariable Long postId) {

        Post postWithCommentById = postService.findPostWithCommentById(postId);
        PostResponse postResponse = new PostResponse(postWithCommentById);
        return postResponse;
    }

    /*
    카테고리 ID로 게시글 리스트 조회 ( 삭제 상태가 아닌 게시글들만, 댓글 수도 함께 조회 )
     */
    @GetMapping("/categories/{categoryId}/posts")
    public List<PostCountDto> findPostWithCommentCount(@PathVariable Long categoryId) {

        List<PostCountDto> postList = postService.findPostList(categoryId);
        return postList;
    }
}
