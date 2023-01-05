package com.throne.system.utils.exception;

import com.throne.system.utils.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 全局异常
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error(requestURI+"发生异常");
        return Result.fail().message(e.getMessage());
    }

    /**
     * 自定义异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(ThroneException.class)
    public Result error(ThroneException e ,HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("ThroneException"+requestURI+"异常");
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }
}
