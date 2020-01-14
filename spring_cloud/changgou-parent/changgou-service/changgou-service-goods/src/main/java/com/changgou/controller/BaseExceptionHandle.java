package com.changgou.controller;
import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


//使用 @ControllerAdvice，不用任何的配置，只要把这个类放在项目中，Spring能扫描到的地方。就可以实现全局异常的回调



@ControllerAdvice
public class BaseExceptionHandle {
    /**
     * 全局异常处理函数
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
