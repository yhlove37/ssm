package com.yuhao.service;

import com.yuhao.entity.PageResult;
import com.yuhao.pojo.CheckGroup;
import com.yuhao.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findById(Integer id);

    List<Integer>  findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);
}
