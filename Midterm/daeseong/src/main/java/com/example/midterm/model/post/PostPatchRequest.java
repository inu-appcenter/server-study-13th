package com.example.midterm.model.post;

import lombok.Data;
import lombok.NonNull;

@Data
public class PostPatchRequest {

    @NonNull
    private String title;

    @NonNull
    private String content;
}
