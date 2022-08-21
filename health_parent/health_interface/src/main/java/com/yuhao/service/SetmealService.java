package com.yuhao.service;

import com.yuhao.entity.PageResult;
import com.yuhao.pojo.Setmeal;

public interface SetmealService {
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
