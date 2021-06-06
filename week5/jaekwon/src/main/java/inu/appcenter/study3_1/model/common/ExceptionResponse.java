package inu.appcenter.study3_1.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExceptionResponse {

    private String message;

    Map<String, String> errorField;
}
