package com.yuhao.dao;

import com.github.pagehelper.Page;
import com.yuhao.pojo.CheckItem;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);

    public long findCountByCheckItemId(Integer id);

    public void deleteById(Integer id);

     public void edit(CheckItem checkItem);

     public CheckItem findById(Integer id);
}
