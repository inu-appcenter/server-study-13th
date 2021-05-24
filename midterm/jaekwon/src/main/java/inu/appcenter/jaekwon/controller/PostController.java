package inu.appcenter.jaekwon.controller;

import com.querydsl.core.Tuple;
import inu.appcenter.jaekwon.domain.Post;
import inu.appcenter.jaekwon.model.post.PostResponseWithComment;
import inu.appcenter.jaekwon.model.post.PostSaveRequest;
import inu.appcenter.jaekwon.model.post.PostUpdateRequest;
import inu.appcenter.jaekwon.model.post.PostWithCommentCount;
import inu.appcenter.jaekwon.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //  게시글 생성
    @PostMapping("/members/{memberId}/categories/{categoryId}/posts")
    public ResponseEntity savePost(@PathVariable Long memberId, @PathVariable Long categoryId
                                    ,@RequestBody @Valid PostSaveRequest postSaveRequest){
        postService.savePost(memberId, categoryId,postSaveRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  게시글 수정
    @PatchMapping("/posts/{postId}")
    public ResponseEntity updatePost(@PathVariable Long postId,
                                     @RequestBody @Valid PostUpdateRequest postUpdateRequest){
        postService.updatePost(postId, postUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //  게시글 조회 + 댓글
    @GetMapping("/posts/{postId}")
    public PostResponseWithComment findWithCommentById(@PathVariable Long postId){
        Post findPost = postService.findWithCommentsById(postId);
        return new PostResponseWithComment(findPost);
    }

    //  카테고리id로 게시글 조회 + 게시글의 댓글 수
    @GetMapping("/categories/{categoryId}/posts")
    public List<PostWithCommentCount> findWithCountByCategoryId(@PathVariable Long categoryId){
        List<PostWithCommentCount> withCountByCategoryId = postService.findWithCountByCategoryId(categoryId);
        return withCountByCategoryId;
    }
}
