package com.yuhao.dao;

import com.github.pagehelper.Page;
import com.yuhao.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);

    public long findCountByCheckItemId(Integer id);

    public void deleteById(Integer id);

     public void edit(CheckItem checkItem);

     public CheckItem findById(Integer id);

     public List<CheckItem> findAll();


     List<CheckItem> findCheckItemById(Integer id);

}
