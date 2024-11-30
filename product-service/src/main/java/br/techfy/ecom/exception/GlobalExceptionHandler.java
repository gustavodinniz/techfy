package br.techfy.ecom.exception;

import br.techfy.ecom.configuration.MessageConfig;

import br.techfy.ecom.dto.FieldErrorDTO;
import br.techfy.ecom.dto.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageConfig messageConfig;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Error when validating fields: {} ", e.getMessage());
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<FieldErrorDTO> errors = fieldErrors
                .stream()
                .map(fe -> new FieldErrorDTO(fe.getField(), fe.getDefaultMessage(),
                        messageConfig.getMessage(fe.getDefaultMessage())))
                .toList();
        return ErrorResponse.builder()
                .message("Error when validating fields.")
                .errors(errors)
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorResponse handleNotFoundException(CategoryNotFoundException e) {
        return ErrorResponse.defaultResponse(e.getMessage(), messageConfig.getMessage(e.getMessage()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse handleNotFoundException(MethodArgumentTypeMismatchException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }
}
