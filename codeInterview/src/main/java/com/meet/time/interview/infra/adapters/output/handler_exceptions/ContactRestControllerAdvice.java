package com.meet.time.interview.infra.adapters.output.handler_exceptions;

import com.meet.time.interview.domain.exceptions.InvalidAccessTokenException;
import com.meet.time.interview.domain.enums.STATUS_RESPONSE_REST_REQUEST;
import com.meet.time.interview.infra.adapters.input.ContactRestController;
import com.meet.time.interview.infra.adapters.input.data.response.global.DefaultRestResponse;
import com.meet.time.interview.infra.adapters.output.handler_exceptions.data.response.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.text.MessageFormat;

import static com.meet.time.interview.domain.utils.MessageConstants.*;

@Slf4j
@RestControllerAdvice(assignableTypes = ContactRestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ContactRestControllerAdvice  extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGeneralException(final Exception ex, final WebRequest request){
        log.error("Handling Exception {}", ex.getMessage());
        final ExceptionResponse exResponse = new ExceptionResponse(INTERNAL_SERVER_ERROR);
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exResponse);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex, final WebRequest request){
        log.error("Handling MethodArgumentTypeMismatchException {}", ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, new ExceptionResponse(MessageFormat.format(FIELD_INVALID , ex.getName())));
    }

    @ExceptionHandler(value = InvalidAccessTokenException.class)
    public ResponseEntity<Object> handleInvalidAccessTokenException(final InvalidAccessTokenException ex, final WebRequest request){
        log.error("Handling InvalidAccessTokenException {}", ex.getMessage());
        return createErrorResponse(ex.getStatus(), ex.getException());
    }

    @ExceptionHandler(value = HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Object> handleHttpClientErrorUnauthorizedException(final HttpClientErrorException ex, final WebRequest request){
        log.error("Handling InvalidAccessTokenException {}", ex.getMessage());
        return createErrorResponse(HttpStatus.UNAUTHORIZED, new ExceptionResponse(INVALID_ACCESS_TOKEN));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode code, final WebRequest request){
        log.error("Handling MethodArgumentNotValidException {}", ex.getMessage());
        System.out.println(ex.getParameter());
        return createErrorResponse(HttpStatus.BAD_REQUEST, new ExceptionResponse(REQUEST_INVALID));
    }

    private ResponseEntity<Object> createErrorResponse(HttpStatus status, ExceptionResponse body){
        return ResponseEntity.status(status).body(new DefaultRestResponse<>(
                STATUS_RESPONSE_REST_REQUEST.ERROR,
                body
        ));
    }

}
