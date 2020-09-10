package com.webms.demo.utils;

import io.vavr.API;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

@RestController
@ControllerAdvice
public class CustomResponseEntityHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        return new ResponseEntity(generateResponse(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserException(Exception ex, WebRequest request) {
        return new ResponseEntity(generateResponse(ex, request), HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity(generateResponse(ex, request), HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse generateResponse(Exception ex, WebRequest request) {
        LocalDate timestamp = LocalDate.now();
        Tuple2<String, String> details = Match(ex).of(
                Case($(instanceOf(MethodArgumentNotValidException.class)),
                        i -> Tuple.of("Validation Error", bindingFailureDetails(i))),
                Case($(), i -> Tuple.of(i.getMessage(), noDetails(request)))
        );
        return new ExceptionResponse(timestamp, details._1, details._2);
    }

    private String noDetails(WebRequest request) {
        return request.getDescription(false);
    }

    private String bindingFailureDetails(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().toString();
    }
}
