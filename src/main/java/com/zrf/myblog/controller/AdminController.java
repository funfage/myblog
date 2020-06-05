package com.zrf.myblog.controller;

import com.zrf.myblog.enums.ResultEnum;
import com.zrf.myblog.pojo.Admin;
import com.zrf.myblog.utils.Result;
import com.zrf.myblog.utils.ShiroUtils;
import com.zrf.myblog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张润发
 * @date 2020/6/5
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        if (admin == null || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())) {
            return new Result(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        try {
            subject.login(token);
        } catch (Exception e) {
            return new Result(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误");
        }
        //登录成功
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }

    /**
     * 获取当前登录用户信息
     * @return
     */
    @GetMapping("/info")
    public Result<Admin> getLoginInfo() {
        Admin admin = (Admin) ShiroUtils.getLoginUser();
        admin.setPassword("");
        return new Result<>(admin);
    }

}
