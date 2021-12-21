package ru.bellintegrator.practice.employee.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.practice.employee.exception.RecordNotFoundException;
import ru.bellintegrator.practice.employee.exception.WrongRequestException;

@RestControllerAdvice
public class ExceptionResponse {

    Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler({WrongRequestException.class, RecordNotFoundException.class,
            HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorView exceptionHandler(RuntimeException e) {
        ErrorView errorView = new ErrorView();
        errorView.error = e.getLocalizedMessage();
        return errorView;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView unexpectedExceptionHandler(Exception e) {
        Long numberError = getRandomIdError();
        LOG.error("Error's number: {}. Error's message: {}",
                numberError, e.getLocalizedMessage());
        ErrorView errorView = new ErrorView();
        errorView.error = "An server error has occurred. Error's number: " + numberError;
        return errorView;
    }

    private Long getRandomIdError() {
        return Math.round(Math.random()*100);
    }
}
