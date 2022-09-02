package com.yuhao.dao;

import com.github.pagehelper.Page;
import com.yuhao.pojo.CheckGroup;
import com.yuhao.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);


    void deleteAssociation(Integer id);

    void edit(CheckGroup checkGroup);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    List<CheckGroup> findAll();

    long findCountBySetmealGroupId(Integer id);

    void deleteById(Integer id);

    long findCountByItemGroupId(Integer id);

    List<CheckGroup> findCheckGroupById(Integer id);
}
