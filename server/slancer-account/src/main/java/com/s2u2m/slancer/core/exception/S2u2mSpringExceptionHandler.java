package com.s2u2m.slancer.core.exception;

import javax.servlet.http.HttpServletRequest;

import com.s2u2m.slancer.core.exception.error.FrameworkCoreErrorCode;
import com.s2u2m.slancer.core.serialization.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class S2u2mSpringExceptionHandler {

    @ExceptionHandler(S2u2mSpringException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse s2u2mExceptionHandler(
            S2u2mSpringException exception, HttpServletRequest httpServletRequest) {
        exception.printStackTrace();
        log.error(exception.toString());
        ErrorResponse response = new ErrorResponse();
        response.setCode(exception.getErrCode());
        response.setErrMsg(exception.getErrMsg());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse exceptionHandler(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        S2u2mSpringException s2u2mSpringException = ExceptionBuilder.build(
                FrameworkCoreErrorCode.Unknown, exception.toString());
        return this.s2u2mExceptionHandler(s2u2mSpringException, request);
    }
}
