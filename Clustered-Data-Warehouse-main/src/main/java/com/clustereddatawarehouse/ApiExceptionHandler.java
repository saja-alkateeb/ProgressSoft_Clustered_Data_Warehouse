package com.clustereddatawarehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleNotFound(EntityNotFoundException e) {
        log.debug("Entity not found.", e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorsDto handleConstraintViolations(ConstraintViolationException e) {
        final List<ErrorDto> errors = e.getConstraintViolations()
                .stream()
                .map(v -> ErrorDto.builder()
                        .message(v.getMessage())
                        .path(v.getPropertyPath().toString())
                        .value(v.getInvalidValue())
                        .build())
                .collect(Collectors.toList());

        return new ErrorsDto(errors);
    }

}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ErrorsDto {
    private List<ErrorDto> errors;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ErrorDto {
    private String message;

    private String path;

    private Object value;
}
