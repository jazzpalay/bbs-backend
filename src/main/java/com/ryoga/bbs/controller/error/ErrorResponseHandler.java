package com.ryoga.bbs.controller.error;

import com.ryoga.bbs.scenario.exception.ScenarioAbortException;
import com.ryoga.bbs.scenario.exception.UnauthorizedException;
import com.ryoga.bbs.scenario.exception.UserNotFoundException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResponseHandler {

    @ExceptionHandler({ScenarioAbortException.class})
    public ResponseEntity<Object> handleScenarioAbort(ScenarioAbortException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(exception.getClass().getSimpleName(), exception.getMessage()));
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleAuthentication(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(exception.getClass().getSimpleName(), exception.getMessage()));
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<Object> handleUnauthorized(UnauthorizedException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(exception.getClass().getSimpleName(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("バリデーションエラー");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("ValidationError", message));
    }

}
