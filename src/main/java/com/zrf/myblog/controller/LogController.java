package com.zrf.myblog.controller;

import com.zrf.myblog.enums.ResultEnum;
import com.zrf.myblog.pojo.Log;
import com.zrf.myblog.service.LogService;
import com.zrf.myblog.utils.Page;
import com.zrf.myblog.utils.Result;
import com.zrf.myblog.utils.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/7
 */
@RestController
@RequestMapping("/log")
@Validated
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @PostMapping(value = "/getByPage")
    public Result<Page<Log>> getByPage(@RequestBody Page<Log> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"log_url", "log_status", "log_method", "log_time", "created_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = logService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> delete(@PathVariable Integer id) {
        logService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.GET)
    public Result<Object> deleteByIds(@RequestBody List<Integer> ids) {
        logService.deleteByIds(ids);
        return new Result<>("删除成功！");
    }

    /**
     * 全部导出
     *
     * @throws Exception
     */
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public void export(HttpServletResponse response) throws Exception {
        Workbook workbook = logService.export();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + "日志");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

}
