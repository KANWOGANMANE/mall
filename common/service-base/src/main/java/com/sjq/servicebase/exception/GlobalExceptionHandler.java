package com.sjq.servicebase.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sjq.commonutils.result.Result;

/**
 * @Author Kemp
 * @create 2022/1/24 11:14
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result error(MyException e){
        e.printStackTrace();
        return Result.fail();
    }



}
