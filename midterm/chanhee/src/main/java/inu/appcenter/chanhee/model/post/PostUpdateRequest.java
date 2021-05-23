package inu.appcenter.chanhee.model.post;

import lombok.Data;

@Data
public class PostUpdateRequest {

    // 게시글 제목과 내용만 변경 가능
    private String title;

    private String content;
}
