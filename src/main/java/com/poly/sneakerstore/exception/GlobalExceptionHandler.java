package com.poly.sneakerstore.exception;

import com.poly.sneakerstore.dto.response.ResponseFailure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AppException.class})
    public ResponseFailure handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return new ResponseFailure(errorCode.getHttpStatus(), errorCode.getMessage(), null);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseFailure handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ex) {
            log.error("INVALID_KEY: {}", enumKey);
        }
        return new ResponseFailure(errorCode.getHttpStatus(), errorCode.getMessage(), null);
    }

    // HttpMessageNotReadableException lỗi khi sai field trong json body

}
