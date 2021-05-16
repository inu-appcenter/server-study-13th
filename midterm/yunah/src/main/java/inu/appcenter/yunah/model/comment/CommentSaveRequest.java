package inu.appcenter.yunah.model.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentRequest {

    @NotBlank
    private String content;

    private Long memberId;

    private Long postId;
}
