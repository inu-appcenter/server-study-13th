package inu.appcenter.yunah.model.todo.request;

import lombok.Data;

@Data  // Getter, Setter 생성
public class TodoSaveRequest {  // DTO(데이터 전달)

    private String content;
}
