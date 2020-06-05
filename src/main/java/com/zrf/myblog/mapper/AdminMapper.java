package com.zrf.myblog.mapper;

import com.zrf.myblog.pojo.Admin;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author  张润发
 */
@Component
public interface AdminMapper {

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Admin getByUsername(String username);
}
