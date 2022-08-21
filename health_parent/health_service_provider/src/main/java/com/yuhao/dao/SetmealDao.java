package com.yuhao.dao;

import com.github.pagehelper.Page;
import com.yuhao.pojo.Setmeal;

import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setSetmealAndCheckGroup(Map<String, Integer> map);

    Page<Setmeal> selectByCondition(String queryString);
}
