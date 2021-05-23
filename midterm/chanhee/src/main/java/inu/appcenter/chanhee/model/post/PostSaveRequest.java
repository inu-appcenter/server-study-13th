package inu.appcenter.chanhee.model.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostSaveRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

}
