package com.example.reggie.common;

/**
 * @author 董成鹏
 * @date 2023/05/04/ 21:56
 */
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}