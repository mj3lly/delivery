package com.jel.delivery.error;

import com.jel.delivery.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        return ResponseEntity.of(Optional.of(ErrorDto
                .builder()
                    .errorCode(HttpStatus.BAD_REQUEST.value())
                    .errorMessage(exception.getMessage())
                .build()));
    }

    @ExceptionHandler(ParcelException.class)
    public ResponseEntity<ErrorDto> handleParcelException(ParcelException exception) {
        return ResponseEntity.of(Optional.of(ErrorDto
                .builder()
                    .errorCode(HttpStatus.BAD_REQUEST.value())
                    .errorMessage(exception.getMessage())
                .build()));
    }

}