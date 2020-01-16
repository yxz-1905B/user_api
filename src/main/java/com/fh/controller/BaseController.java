package com.fh.controller;

import com.fh.util.ResponseData;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
        全局异常统一处理
 */
@ControllerAdvice
public class BaseController {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseData methodError(HttpRequestMethodNotSupportedException e){
        e.printStackTrace();//打印出异常 堆栈信息
        System.out.println("请求方式错误");
        return ResponseData.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseData handleException(Exception e){
        e.printStackTrace();//打印出异常 堆栈信息
        System.out.println("handleMyException....");
        return ResponseData.error(e.getMessage());
    }



}
