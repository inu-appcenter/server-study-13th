package com.example.midterm.model.category;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategorySaveRequest {

    @NotNull
    private String content;
}
