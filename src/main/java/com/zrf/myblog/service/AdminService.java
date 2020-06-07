package com.zrf.myblog.service;

import com.zrf.myblog.mapper.AdminMapper;
import com.zrf.myblog.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author 张润发
 */
public interface AdminService {

    /**
     * 根据用户名查询
     *
     * @param username
     * @return
     */
    Admin getByUsername(String username);

    /**
     * 查询管理员
     * @return
     */
    Admin getAdmin();

    /**
     * 更新个人信息
     * @param admin
     */
    void updateInfo(Admin admin);

    /**
     * 更新密码
     * @param admin
     */
    void updatePassword(Admin admin);
}
