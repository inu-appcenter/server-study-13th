package inu.appcenter.yunah.model.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentUpdateRequest {

    @NotBlank
    private String content;
}
