package ru.cinimex.deveducate.rest.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
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
                "Некорректный запрос! " + ex.getMessage()
        );
    }

    @ExceptionHandler(value = {ServerErrorException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage internalServerException(ServerErrorException ex, WebRequest request) {
        return new ErrorMessage(
                "Ошибка сервера!" + ex.getMessage()
        );
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exception(Exception ex, WebRequest request) {
        return new ErrorMessage(
                "Неизвестная ошибка сервера! " + ex.getMessage() + "  getLocalizedMessage:"+ex.getLocalizedMessage() +
                        " StackTrace:" + ex.getStackTrace().toString() + " Request:" + request.getContextPath() + "; "+
                        request.getDescription(false) + "; Parameters:" + request.getParameterMap() +
                        "; ParameterNames" + request.getParameterNames()
        );
    }


    @ExceptionHandler(value = {UnsupportedOperationException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage unsupportedOperationException(UnsupportedOperationException ex, WebRequest request) {
        return new ErrorMessage(
                "Операция не поддерживается!"
        );
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public ErrorMessage illegalStateException(IllegalStateException ex, WebRequest request) {
        return new ErrorMessage(
                "Неизвестное исключение! " + ex.getMessage()
        );
    }
}
