package com.zrf.myblog.service;

import com.zrf.myblog.pojo.Link;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author 张润发
 */
public interface LinkService {

    /**
     * 添加
     * @param link
     */
    void save(Link link);

    /**
     * 更新
     * @param link
     */
    void update(Link link);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Link> getAll();

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);
}
