package com.example.midterm.model.comment;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CommentPatchRequest {

    @NotNull
    private String content;
}
