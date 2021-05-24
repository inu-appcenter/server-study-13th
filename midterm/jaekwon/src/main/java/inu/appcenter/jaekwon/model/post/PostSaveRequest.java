package inu.appcenter.jaekwon.model.post;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PostSaveRequest {

    @NotNull
    private String title;

    @NotNull
    private String content;
}
