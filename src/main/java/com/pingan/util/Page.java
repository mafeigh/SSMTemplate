package com.pingan.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class Page extends PageInfo {

    //排序的数据库列名
    private String orderColumn;

    //排序方式：asc or desc
    private String orderSort;

    public Page() {
    }

    public Page(List list) {
        super(list);
    }

    @Override
    public int getPageSize() {
        return super.getPageSize() == 0 ? 10 : super.getPageSize();
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderSort() {
        return orderSort;
    }

    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }
}
