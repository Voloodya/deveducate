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
import java.util.Arrays;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final String GETLOCALIZED_MESSAGE = " \n GetLocalizedMessage:";
    private static final String STACK_TRACE = "; \n  StackTrace:";
    private static final String REQUEST = "; \n Request:";
    private static final String DESCRIPTION = "; \n  Description:";
    private static final String PARAMETERS = "; \n Parameters:";
    private static final String PARAMETER_NAMES = "; \n ParameterNames:";

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
                "Ошибка сервера!" + ex.getMessage() +
                        GETLOCALIZED_MESSAGE + ex.getLocalizedMessage() +
                        STACK_TRACE + Arrays.toString(ex.getStackTrace()) +
                        REQUEST + request.getContextPath() +
                        DESCRIPTION + request.getDescription(false) +
                        PARAMETERS + request.getParameterMap() +
                        PARAMETER_NAMES + request.getParameterNames()
        );
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage exception(Exception ex, WebRequest request) {
        return new ErrorMessage(
                "Неизвестная ошибка сервера! " + ex.getMessage() +
                GETLOCALIZED_MESSAGE + ex.getLocalizedMessage() +
                STACK_TRACE + Arrays.toString(ex.getStackTrace()) +
                REQUEST + request.getContextPath() +
                DESCRIPTION + request.getDescription(false) +
                PARAMETERS + request.getParameterMap() +
                PARAMETER_NAMES + request.getParameterNames()
        );
    }


    @ExceptionHandler(value = {UnsupportedOperationException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage unsupportedOperationException(UnsupportedOperationException ex, WebRequest request) {
        return new ErrorMessage(
                "Операция не поддерживается! " + ex.getMessage() +
                        GETLOCALIZED_MESSAGE + ex.getLocalizedMessage() +
                        STACK_TRACE + Arrays.toString(ex.getStackTrace()) +
                        REQUEST + request.getContextPath() +
                        DESCRIPTION + request.getDescription(false) +
                        PARAMETERS + request.getParameterMap() +
                        PARAMETER_NAMES + request.getParameterNames()
        );
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public ErrorMessage illegalStateException(IllegalStateException ex, WebRequest request) {
        return new ErrorMessage(
                "Неизвестное исключение! " + ex.getMessage() +
                        GETLOCALIZED_MESSAGE + ex.getLocalizedMessage() +
                        STACK_TRACE + Arrays.toString(ex.getStackTrace()) +
                        REQUEST + request.getContextPath() +
                        DESCRIPTION + request.getDescription(false) +
                        PARAMETERS + request.getParameterMap() +
                        PARAMETER_NAMES + request.getParameterNames()
        );
    }

}
