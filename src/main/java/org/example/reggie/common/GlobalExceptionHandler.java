package org.example.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * SQL异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> SQLExceptionHandler(SQLIntegrityConstraintViolationException e){
        log.error(e.getMessage());
        if (e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split(" ");
            String s = split[2] + "已存在";
            return R.error(s);
        }
        return R.error("未知错误");
    }

    /**
     * RuntimeException异常处理方法
     * @param runtimeException
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public R<String> RuntimeExceptionHandler(RuntimeException runtimeException){
        log.error(runtimeException.getMessage());
        return R.error(runtimeException.getMessage());
    }
}
