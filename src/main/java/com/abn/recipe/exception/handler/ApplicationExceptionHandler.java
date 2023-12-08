package com.abn.recipe.exception.handler;


import com.abn.recipe.exception.ErrorResponse;
import com.abn.recipe.exception.IncorrectDishTypeException;
import com.abn.recipe.exception.RecipeExistsException;
import com.abn.recipe.exception.RecipeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ErrorResponse> customException(final RecipeNotFoundException exception) {
        log.error("Invalid config {}", exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecipeExistsException.class)
    public ResponseEntity<ErrorResponse> customException(final RecipeExistsException exception) {
        log.error("Invalid config {}", exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.OK.toString())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @ExceptionHandler(IncorrectDishTypeException.class)
    public ResponseEntity<ErrorResponse> customException(final IncorrectDishTypeException exception) {
        log.error("Invalid config {}", exception.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.toString())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
