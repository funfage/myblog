package com.zrf.myblog.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 张润发
 */
@Data
public class About implements Serializable {

    private Integer aboutId;
    private String aboutTitle;
    private String aboutContent;
    private Integer aboutRead;
    private String createdTime;
    private String updateTime;
    private Integer version;
    private Integer enable;
    private Integer deleted;

}
