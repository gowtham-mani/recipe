package com.abn.recipe.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude (JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final String code;
    private final String message;
}
