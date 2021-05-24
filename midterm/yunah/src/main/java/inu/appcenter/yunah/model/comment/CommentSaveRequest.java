package inu.appcenter.yunah.model.comment;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentSaveRequest {

    @NotBlank
    private String content;
}
