package com.zrf.myblog.pojo;

import lombok.Data;

/**
 * 友情链接
 * @author 张润发
 */
@Data
public class Link {

    private Integer linkId;
    private String linkName;
    private String linkUrl;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer deleted;

}
