package com.zrf.myblog.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 点赞实体
 * @author 张润发
 */
@Data
public class BlogGoods {

    @Id
    private String id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 博客id
     */
    private String blogId;

}
