package com.lambda.error;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@SuppressWarnings("deprecation")
public class ControllerErrorHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleAllException(Exception ex, WebRequest request) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        return new ApiError(9999, ex.getLocalizedMessage());
    }

    /**
     * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleBusinessException(BusinessException ex, WebRequest request) {
        return new ApiError(ex.getCode(), ex.getLocalizedMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {
        return new ApiError(2000, ex.getLocalizedMessage());
    }
}