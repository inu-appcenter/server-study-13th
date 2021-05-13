package inu.appcenter.chanhee.model.todo;

import lombok.Data;

@Data
// 완료 여부만 변경
public class TodoUpdateRequest {

    private String isCompleted;
}
