package com.zrf.myblog.service.impl;

import com.zrf.myblog.enums.StateEnums;
import com.zrf.myblog.mapper.MusicMapper;
import com.zrf.myblog.pojo.Music;
import com.zrf.myblog.service.MusicService;
import com.zrf.myblog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author 张润发
 */
@Service
@Slf4j
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }

    @Override
    public Music getById(Integer id) {
        return musicMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        musicMapper.deleteById(id);
    }

    @Override
    public void enableById(Integer id) {
        Music music = musicMapper.getById(id);
        if (music != null) {
            music.setEnabled(StateEnums.ENABLED.getCode());
            musicMapper.update(music);
        }
    }

    @Override
    public void disableById(Integer id) {
        Music music = musicMapper.getById(id);
        if (music != null) {
            music.setEnabled(StateEnums.NOT_ENABLED.getCode());
            musicMapper.update(music);
        }
    }

    @Override
    public Page<Music> getByPage(Page<Music> page) {
        // 查询数据
        List<Music> musicList = musicMapper.getByPage(page);
        page.setList(musicList);
        // 查询总数
        int totalCount = musicMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<Music> getList() {
        return musicMapper.getList();
    }
}
