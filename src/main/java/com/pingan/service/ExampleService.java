package com.pingan.service;

import com.github.pagehelper.PageHelper;
import com.pingan.mapper.ApplyMapper;
import com.pingan.model.Apply;
import com.pingan.util.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class ExampleService {
    public void setApplyMapper(ApplyMapper applyMapper) {
        this.applyMapper = applyMapper;
    }

    @Resource
    private ApplyMapper applyMapper;

    public Apply getApply(Integer id) {
        return applyMapper.selectByPrimaryKey(id);
    }

    public List<Apply> getApplys() {
        return applyMapper.selectList();
    }

    public Integer getApplyCount() {
        return applyMapper.selectItemCount();
    }

    public Page getApplyByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());//设置分页参数
        if (StringUtils.isNotBlank(page.getOrderColumn())) {//如果需要排序
            PageHelper.orderBy(page.getOrderColumn() + " " + page.getOrderSort());
        }
        List list = applyMapper.selectList();
        Page pg = new Page(list);
        pg.setOrderColumn(page.getOrderColumn());
        pg.setOrderSort(page.getOrderSort());
        return pg;
    }
}
