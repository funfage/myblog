package com.zrf.myblog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 管理员表实体类
 * </p>
 *
 * @author 张润发
 */
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 169915810554522554L;

    /**
     * 管理员id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String header;

    /**
     * 签名
     */
    private String signature;

    /**
     * 介绍
     */
    private String comment;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
