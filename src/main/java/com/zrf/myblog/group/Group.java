package com.zrf.myblog.group;

import javax.validation.GroupSequence;

/**
 * @author 张润发
 * @date 2020/6/7
 */

@GroupSequence({Insert.class, Update.class})
public interface Group {
}
