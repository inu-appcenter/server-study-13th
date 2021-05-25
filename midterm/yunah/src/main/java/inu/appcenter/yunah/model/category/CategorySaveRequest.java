package inu.appcenter.yunah.model.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategorySaveRequest {

    @NotBlank
    private String content;
}
