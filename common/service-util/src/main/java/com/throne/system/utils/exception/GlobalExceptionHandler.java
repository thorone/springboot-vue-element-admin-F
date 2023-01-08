package com.throne.system.utils.exception;

import com.throne.system.utils.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @ExceptionHandler(BindException.class)
    public Result error(BindException e,HttpServletRequest request){
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String defaultMessage = fieldError.getDefaultMessage();
        }
        return Result.fail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result error(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            String defaultMessage = fieldError.getDefaultMessage();
            sb.append(defaultMessage).append(";");
        }
        return Result.fail().message(sb.toString());
    }
}
