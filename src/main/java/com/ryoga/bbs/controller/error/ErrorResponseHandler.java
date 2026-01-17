package com.ryoga.bbs.controller.error;

import com.ryoga.bbs.scenario.exception.ScenarioAbortException;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthentication(AuthenticationException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(exception.getClass().getSimpleName(), exception.getMessage()));
    }

}
