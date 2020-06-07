package com.zrf.myblog.controller;

import com.zrf.myblog.enums.ResultEnum;
import com.zrf.myblog.pojo.Admin;
import com.zrf.myblog.service.AdminService;
import com.zrf.myblog.utils.Result;
import com.zrf.myblog.utils.ShiroUtils;
import com.zrf.myblog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AdminService adminService;

    /**
     * 用户登录
     * @param admin
     * @return
     */
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

    /**
     * 查询管理员
     * @return
     */
    @GetMapping("/getAdmin")
    public Result<Object> getAdmin() {
        Admin admin = adminService.getAdmin();
        return new Result<>(admin);
    }

    /**
     * 更新个人信息
     * @param admin
     * @return
     */
    @PutMapping("/updateInfo")
    public Result<Object> updateInfo(@RequestBody Admin admin) {
        if (admin.getId() == null) {
            return new Result<>(ResultEnum.PARAMS_NULL, "id");
        }
        adminService.updateInfo(admin);
        return new Result<>("更新成功！");
    }

    /**
     * 更新密码
     * @param admin
     * @return
     */
    @PutMapping("/updatePassword")
    public Result<Object> updatePassword(@RequestBody Admin admin) {
        if (StringUtils.isBlank(admin.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),
                    "密码" + ResultEnum.PARAMS_NULL.getMsg());
        }
        adminService.updatePassword(admin);
        return new Result<>("更新成功！");
    }

}
