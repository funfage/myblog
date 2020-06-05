package com.zrf.myblog.advice;

import com.zrf.myblog.enums.ResultEnum;
import com.zrf.myblog.exception.BlogException;
import com.zrf.myblog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 张润发
 * @date 2020/6/4
 */
@ControllerAdvice
@Slf4j
public class BlogExceptionAdvice {

    /**
     * 统一处理 BlogException
     * @param e
     */
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException e) {
        log.error("自定义统一异常处理:" , e);
        return new Result<>(e.getErrorCode(), e.getMessage());
    }

    /**
     * 空异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result<Object> nullPointerExceptionHandler(NullPointerException e) {
        log.error("空异常处理:", e);
        return new Result<>(-1, e.getMessage());
    }

    /**
     * 公共异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> commonExceptionHandler(Exception e) {
        log.error("未知异常处理:", e);
        return new Result<>(-1, e.getMessage());
    }

}
