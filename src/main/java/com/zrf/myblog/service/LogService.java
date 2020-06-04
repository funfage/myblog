package com.zrf.myblog.service;

import com.zrf.myblog.pojo.Log;
import com.zrf.myblog.utils.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务层接口
 * </p>
 *
 * @author 张润发
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 *
 */
@Service
public interface LogService {

    /**
     * 保存
     * @param logger
     */
    void save(Log logger);

    /**
     * 分页查询
     * @param page
     * @return
     */
    Page<Log> getByPage(Page<Log> page);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id集合删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 查询数据，构建成workbook用于导出
     * @return
     */
//    Workbook export();
}
