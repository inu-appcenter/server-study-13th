package com.example.hw3.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private String message;

    Map<String, String> errorField;
}
