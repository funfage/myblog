package com.zrf.myblog.service;

import com.zrf.myblog.pojo.Blog;
import com.zrf.myblog.utils.Page;
import com.zrf.myblog.vo.BlogVo;

/**
 * <p>
 * 博客表服务层接口
 * </p>
 *
 * @author 张润发
 */
public interface BlogService {


    /**
     * 保存
     * @param blog
     */
    void save(Blog blog);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 根据id更新
     * @param blog
     */
    void update(Blog blog);

    /**
     * 根据id查询阅读的博客
     * @param id
     * @return
     */
    BlogVo readById(String id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);
}
