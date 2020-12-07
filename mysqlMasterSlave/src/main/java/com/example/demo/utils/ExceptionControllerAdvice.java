package com.example.demo.utils;

import com.example.demo.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    SendMailUtil sendMailUtil;


    @ExceptionHandler(ArithmeticException.class)
    public ResultVO<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        sendMailUtil.sendMail("765783376@qq.com","系统出现异常",e.getMessage());
        return new ResultVO<String>(ResultCode.FAILED,e.getMessage());
    }
    
    @ExceptionHandler(APIException.class)
    public ResultVO<String> APIExceptionHandler(APIException e) {
        //如果出现异常，发邮件通知
        sendMailUtil.sendMail("765783376@qq.com","系统出现异常","错误信息："+e.getMsg());
        return new ResultVO<String>(ResultCode.ERROR, e.getMsg());
    }
}
