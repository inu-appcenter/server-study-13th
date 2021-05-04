package inu.appcenter.yunah.model.todo.request;

import lombok.Data;

@Data
public class TodoUpdateRequest {

    private boolean isCompleted;

    public boolean getIsCompleted() {
        return isCompleted;
    }
}
