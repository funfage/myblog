package com.zrf.myblog.advice;

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
     *
     * @param exception
     */
    @ExceptionHandler(BlogException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(BlogException exception) {
        log.error("统一异常处理：", exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }

}
