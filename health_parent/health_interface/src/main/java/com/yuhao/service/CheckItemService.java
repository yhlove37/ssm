package com.yuhao.service;

import com.yuhao.entity.PageResult;
import com.yuhao.pojo.CheckItem;

public interface CheckItemService {
    public void add(CheckItem checkItem);
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

}
