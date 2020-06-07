package com.zrf.myblog.controller;

import com.zrf.myblog.enums.ResultEnum;
import com.zrf.myblog.group.Insert;
import com.zrf.myblog.group.Update;
import com.zrf.myblog.pojo.About;
import com.zrf.myblog.service.AboutService;
import com.zrf.myblog.utils.Page;
import com.zrf.myblog.utils.Result;
import com.zrf.myblog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张润发
 * @date 2020/6/7
 */
@RestController
@RequestMapping("/about")
@Validated
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * 保存
     *
     * @param about
     * @return
     */
    @PostMapping(value = "/save")
    public Result<Object> save(@Validated({Insert.class}) @RequestBody About about) {
        aboutService.save(about);
        return new Result<>("添加成功！");
    }

    /**
     * 更新
     *
     * @param about
     * @return
     */
    @PutMapping(value = "/update")
    public Result<Object> update(@Validated({Update.class}) @RequestBody About about) {
        aboutService.update(about);
        return new Result<>("修改成功！");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Result<About> get(@PathVariable @Min(value = 0, message = "id不能小于0！") Integer id) {
        About about = aboutService.getById(id);
        return new Result<>(about);
    }

    /**
     * 阅读
     *
     * @return
     */
    @GetMapping(value = "/read")
    public Result<About> read() {
        About about = aboutService.read();
        return new Result<>(about);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result<Object> delete(@PathVariable Integer id) {
        aboutService.deleteById(id);
        return new Result<>("删除成功！");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @PutMapping(value = "/enable/{id}")
    public Result<Object> enable(@PathVariable Integer id) {
        aboutService.enableById(id);
        return new Result<>("启用成功");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @PutMapping(value = "/disable/{id}")
    public Result<Object> disable(@PathVariable Integer id) {
        aboutService.disableById(id);
        return new Result<>("弃用成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<About>> getByPage(@RequestBody Page<About> page) {
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNotBlank(sortColumn)) {
            // 排序列不为空
            String[] sortColumns = {"about_read", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = aboutService.getByPage(page);
        return new Result<>(page);
    }

}
