package com.zzz.learn.common.exception;

import com.zzz.learn.common.ResponseData;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData jsonErrorHandler(HttpServletRequest req, Exception e) {
        if(e instanceof GlobalException){
            GlobalException ge = (GlobalException)e;
            ResponseData r = new ResponseData();
            r.setMessage(ge.getMessage());
            r.setCode(ge.getCode());
            r.setData(null);
            r.setStatus(false);
            return r;
        }else{
            ResponseData r = new ResponseData();
            r.setMessage(e.getMessage());
            if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
                r.setCode(404);
            } else {
                r.setCode(500);
            }
            r.setData(null);
            r.setStatus(false);
            return r;
        }
    }
}