package com.admin.controller;

import com.admin.utils.Result;
import com.admin.utils.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(StatusCode.ERROR, e.getMessage(),"");
    }
}
