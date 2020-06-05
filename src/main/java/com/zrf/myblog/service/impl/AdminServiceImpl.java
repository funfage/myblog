package com.zrf.myblog.service.impl;

import com.zrf.myblog.mapper.AdminMapper;
import com.zrf.myblog.pojo.Admin;
import com.zrf.myblog.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表服务实现类
 * </p>
 *
 * @author 张润发
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getByUsername(String username) {
        return adminMapper.getByUsername(username);
    }
}
