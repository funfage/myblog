package com.zrf.myblog.pojo;

import lombok.Data;

/**
 * @author 张润发
 */
@Data
public class Music {

    private Integer id;
    private String name;
    private String artist;
    private String url;
    private String cover;
    private String lrc;
    private String createdTime;
    private Integer enabled;
    private Integer deleted;

}
