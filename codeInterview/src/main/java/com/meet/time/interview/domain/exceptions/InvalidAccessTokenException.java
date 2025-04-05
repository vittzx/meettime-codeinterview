package com.meet.time.interview.domain.exceptions;


import com.meet.time.interview.infra.adapters.output.handler_exceptions.data.response.ExceptionResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidAccessTokenException extends RuntimeException{

    private final HttpStatus status;
    private final ExceptionResponse exception;

    public InvalidAccessTokenException(ExceptionResponse ex){
       super(ex.getMessage());
       this.exception = ex;
       this.status = HttpStatus.UNAUTHORIZED;
   }
}
