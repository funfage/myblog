package com.zrf.myblog.service.impl;

import com.zrf.myblog.enums.ResultEnum;
import com.zrf.myblog.enums.StateEnums;
import com.zrf.myblog.exception.BlogException;
import com.zrf.myblog.mapper.AboutMapper;
import com.zrf.myblog.pojo.About;
import com.zrf.myblog.service.AboutService;
import com.zrf.myblog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author 稽哥
 */
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {

    @Autowired
    private AboutMapper aboutMapper;

    @Override
    public void save(About about) {
        About oldAbout = aboutMapper.getAbout();
        if (oldAbout != null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "只能添加一个”关于“");
        }
        aboutMapper.save(about);
    }

    @Override
    public void update(About about) {
        aboutMapper.updateById(about);
    }

    @Override
    public About getById(Integer id) {
        return aboutMapper.getById(id);
    }

    @Override
    public About read() {
        About about = aboutMapper.getAbout();
        if (about == null) {
            return null;
        }
        // 更新阅读数
        about.setAboutRead(about.getAboutRead() + 1);
        aboutMapper.updateById(about);
        return about;
    }

    @Override
    public void deleteById(Integer id) {
        aboutMapper.deleteById(id);
    }

    @Override
    public void enableById(Integer id) {
        // 查询是否已经存在启用的关于我的
        About about = aboutMapper.getAbout();
        if (about != null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "当前已存在启用中的关于我的");
        }
        about = aboutMapper.getById(id);
        if (about != null) {
            about.setEnable(StateEnums.ENABLED.getCode());
            aboutMapper.updateEnable(about);
        }
    }

    @Override
    public void disableById(Integer id) {
        About about = aboutMapper.getById(id);
        if (about != null) {
            about.setEnable(StateEnums.NOT_ENABLED.getCode());
            aboutMapper.updateEnable(about);
        }
    }

    @Override
    public Page<About> getByPage(Page<About> page) {
        // 查询数据
        List<About> aboutList = aboutMapper.getByPage(page);
        page.setList(aboutList);
        // 查询总数
        int totalCount = aboutMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
