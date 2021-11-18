package ru.cinimex.deveducate.rest.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ServerErrorException;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ErrorMessage(
                "Покупатель не найден! " + ex.getMessage()
        );
    }

    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException(ValidationException ex, WebRequest request) {
        return new ErrorMessage(
                "Некорректный запрос! "+ ex.getMessage()
        );
    }

    @ExceptionHandler(value = {ServerErrorException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage internalServerException(ServerErrorException ex, WebRequest request) {
        return new ErrorMessage(
                "Ошибка сервера!"
        );
    }
}
