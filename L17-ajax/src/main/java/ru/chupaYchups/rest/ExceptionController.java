package ru.chupaYchups.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.chupaYchups.exception.LibraryException;
import ru.chupaYchups.exception.NoSuchBookException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NoSuchBookException.class)
    public ResponseEntity<String> handleException(LibraryException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
