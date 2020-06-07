package com.zrf.myblog.service.impl;


import com.zrf.myblog.excel.entity.ExportParams;
import com.zrf.myblog.excel.handler.ExcelExportHandler;
import com.zrf.myblog.mapper.LogMapper;
import com.zrf.myblog.pojo.Log;
import com.zrf.myblog.service.LogService;
import com.zrf.myblog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author 张润发
 *
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    /**
     * 保存
     * @param logger
     */
    @Override
    public void save(Log logger) {
        logMapper.save(logger);
    }

    @Override
    public Page<Log> getByPage(Page<Log> page) {
        // 查询数据
        List<Log> logList = logMapper.getByPage(page);
        page.setList(logList);
        // 查询总数
        int totalCount = logMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void deleteById(Integer id) {
        logMapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        logMapper.deleteByIds(ids);
    }

    @Override
    public Workbook export() {
        List<Log> logList = logMapper.getAll();
        return new ExcelExportHandler().createSheet(new ExportParams("最新日志", "sheet1"), Log.class, logList);
    }
}
