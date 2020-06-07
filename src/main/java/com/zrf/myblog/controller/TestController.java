package com.zrf.myblog.controller;

import com.zrf.myblog.exception.BlogException;
import com.zrf.myblog.pojo.Admin;
import com.zrf.myblog.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 张润发
 * @date 2020/6/5
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试异常
     *
     * @return
     */
    @GetMapping("/testException/{id}")
    public Result<Object> testException(@PathVariable Integer id) {
        if (id == 1) {
            return new Result<>();
        } else {

            throw new BlogException("发生了异常!");
        }
    }

    @GetMapping("testjson")
    public Result<Object> testJson() {
        Admin admin = new Admin();
        admin.setUsername("111");
        admin.setId(1);
        admin.setSignature("394835");
        return new Result<>(admin);
    }

    @GetMapping("testjson1")
    public Admin testJson1() {
        Admin admin = new Admin();
        admin.setUsername("111");
        admin.setId(1);
        admin.setSignature("394835");
        admin.setPassword(null);
        return admin;
    }



}
