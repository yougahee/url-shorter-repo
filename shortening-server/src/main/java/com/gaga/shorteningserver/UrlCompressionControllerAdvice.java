package com.gaga.shorteningserver;

import com.gaga.shorteningserver.exception.InvalidProtocolException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class UrlCompressionControllerAdvice {

    @ExceptionHandler(value = {InvalidProtocolException.class})
    public ResponseEntity<String> handleInvalidProtocolException(InvalidProtocolException e){
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<String> handleIllegalException(IllegalArgumentException iae){
        log.error(iae.getMessage(), iae);
        return ResponseEntity.badRequest().body(iae.getMessage());
    }
}
